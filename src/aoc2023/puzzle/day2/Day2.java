package aoc2023.puzzle.day2;

import io.Reader;
import model.Day;
import utils.UnicodeConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Day2 extends Day {

    private final List<String> input;

    private final String RED_KEY = "red";
    private final String GREEN_KEY = "green";
    private final String BLUE_KEY = "blue";

    public Day2() {
        super("Cube Conundrum");
        this.input = Reader.readAsList(getInputPath());
        super.setAsciiLine("    ----@        '''..*......'''                   ");
    }

    /**
     * You're launched high into the atmosphere! The apex of your trajectory just barely reaches the surface of a large
     * island floating in the sky. You gently land in a fluffy pile of leaves. It's quite cold, but you don't see much
     * snow. An Elf runs over to greet you.
     * <br><br>
     * The Elf explains that you've arrived at <b>Snow Island</b> and apologizes for the lack of snow. He'll be happy to
     * explain the situation, but it's a bit of a walk, so you have some time. They don't get many visitors up here;
     * would you like to play a game in the meantime?
     * <br><br>
     * As you walk, the Elf shows you a small bag and some cubes which are either red, green, or blue. Each time you
     * play this game, he will hide a secret number of cubes of each color in the bag, and your goal is to figure out
     * information about the number of cubes.
     * <br><br>
     * To get information, once a bag has been loaded with cubes, the Elf will reach into the bag, grab a handful of
     * random cubes, show them to you, and then put them back in the bag. He'll do this a few times per game.
     * <br><br>
     * You play several games and record the information from each game (your puzzle input). Each game is listed with
     * its ID number (like the 11 in Game 11: ...) followed by a semicolon-separated list of subsets of cubes that were
     * revealed from the bag (like 3 red, 5 green, 4 blue).
     * <br><br>
     * Determine which games would have been possible if the bag had been loaded with only 12 red cubes, 13 green cubes,
     * and 14 blue cubes.
     * <br><br>
     * <b>What is the sum of the IDs of those games?</b>
     */
    @Override
    public void solveLvl1() {
        Predicate<String> predicate = game -> this.getMappedSubsets(game)
                .stream().allMatch(this::isSubsetPossible);

        int gameIdSum = input.stream().filter(predicate)
                .mapToInt(this::getGameId).sum();

        super.setLvl1Answer(gameIdSum); // 2278
    }

    /**
     * Checks if the given subset is a possible game solution.
     * @param subset the game subset mapped by color
     * @return {@code true} if subset colors are within color bounds for a possible game, otherwise {@code false}
     */
    private boolean isSubsetPossible(Map<String, Integer> subset) {
        final int MAX_RED_CUBES = 12;
        final int MAX_GREEN_CUBES = 13;
        final int MAX_BLUE_CUBES = 14;
        final boolean isRedPossible = subset.get(RED_KEY) <= MAX_RED_CUBES;
        final boolean isGreenPossible = subset.get(GREEN_KEY) <= MAX_GREEN_CUBES;
        final boolean isBluePossible = subset.get(BLUE_KEY) <= MAX_BLUE_CUBES;
        return isRedPossible && isGreenPossible && isBluePossible;
    }

    /**
     * The Elf says they've stopped producing snow because they aren't getting any <b>water</b>! He isn't sure why the
     * water stopped; however, he can show you how to get to the water source to check it out for yourself. It's just up
     * ahead!
     * <br><br>
     * As you continue your walk, the Elf poses a second question: in each game you played, what is the <b>fewest number
     * of cubes of each color</b> that could have been in the bag to make the game possible?
     * <br><br>
     * The <b>power</b> of a set of cubes is equal to the numbers of red, green, and blue cubes multiplied together.
     * <br><br>
     * For each game, find the minimum set of cubes that must have been present.
     * <br><br>
     * <b>What is the sum of the power of these sets?</b>
     */
    @Override
    public void solveLvl2() {
        int gamePowerSum = 0;

        for (String game : input) {
            int minRedCubes = 0;
            int minGreenCubes = 0;
            int minBlueCubes = 0;

            for (Map<String, Integer> subset : this.getMappedSubsets(game)) {
                minRedCubes = Math.max(minRedCubes, subset.get(RED_KEY));
                minGreenCubes = Math.max(minGreenCubes, subset.get(GREEN_KEY));
                minBlueCubes = Math.max(minBlueCubes, subset.get(BLUE_KEY));
            }

            gamePowerSum += minRedCubes * minGreenCubes * minBlueCubes;
        }

        super.setLvl2Answer(gamePowerSum); // 67953
    }

    /**
     * Returns a list of all game subsets mapped by color.
     * @param game the game to be mapped
     * @return a list of all mapped game subsets
     */
    private List<Map<String, Integer>> getMappedSubsets(String game) {
        List<Map<String, Integer>> mappedSubsets = new ArrayList<>();

        for (String subset : this.getSubsets(game)) {
            Map<String, Integer> colorMap = this.addInitialColorMap(mappedSubsets);
            for (String group : subset.split(UnicodeConst.COMMA_SPACE)) {
                String[] groupProperties = group.split(UnicodeConst.SPACE);
                int amount = Integer.parseInt(groupProperties[0]);
                String color = groupProperties[1];
                colorMap.put(color, amount);
            }
        }

        return mappedSubsets;
    }

    /**
     * Adds and returns a map initialized with 0 values that will be used to map some subset by color.
     * @param subsets the list of game subsets
     * @return the initialized map
     */
    private Map<String, Integer> addInitialColorMap(List<Map<String, Integer>> subsets) {
        Map<String, Integer> colorMap = new HashMap<>();
        final int DEFAULT_VALUE = 0;
        colorMap.put(RED_KEY, DEFAULT_VALUE);
        colorMap.put(GREEN_KEY, DEFAULT_VALUE);
        colorMap.put(BLUE_KEY, DEFAULT_VALUE);
        subsets.add(colorMap);
        return colorMap;
    }

    /**
     * Splits and returns an array of game subsets.
     * @param game the game string input
     * @return an array of all subsets of the game
     */
    private String[] getSubsets(String game) {
        final String rightSideOfColon = game.split(UnicodeConst.COLON_SPACE)[1];
        return rightSideOfColon.split(UnicodeConst.SEMICOLON_SPACE);
    }

    /**
     * Retrieves the game ID from the game input.
     * @param game the game string input
     * @return the game ID
     */
    private int getGameId(String game) {
        final String leftSideOfColon = game.split(UnicodeConst.COLON)[0];
        final String gameId = leftSideOfColon.substring("Game ".length());
        return Integer.parseInt(gameId);
    }
}
