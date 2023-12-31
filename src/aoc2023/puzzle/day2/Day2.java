package aoc2023.puzzle.day2;

import io.Reader;
import model.Day;
import utils.UnicodeConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        final int MAX_RED_CUBES = 12;
        final int MAX_GREEN_CUBES = 13;
        final int MAX_BLUE_CUBES = 14;

        int gameIndexCount = 0;

        for (String game : input) {
            boolean isGamePossible = true;

            for (Map<String, Integer> subset : this.getGameSubsets(game)) {
                if (subset.get(RED_KEY) > MAX_RED_CUBES
                        || subset.get(GREEN_KEY) > MAX_GREEN_CUBES
                        || subset.get(BLUE_KEY) > MAX_BLUE_CUBES) {
                    isGamePossible = false;
                }
            }

            if (isGamePossible) {
                gameIndexCount += this.getGameIndex(game);
            }
        }

        super.setLvl1Answer(gameIndexCount); // 2278
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
        int gamePower = 0;

        for (String game : input) {
            int minRedCubes = 0;
            int minGreenCubes = 0;
            int minBlueCubes = 0;

            for (Map<String, Integer> subset : this.getGameSubsets(game)) {
                minRedCubes = Math.max(minRedCubes, subset.get(RED_KEY));
                minGreenCubes = Math.max(minGreenCubes, subset.get(GREEN_KEY));
                minBlueCubes = Math.max(minBlueCubes, subset.get(BLUE_KEY));
            }

            gamePower += minRedCubes * minGreenCubes * minBlueCubes;
        }

        super.setLvl2Answer(gamePower); // 67953
    }

    private List<Map<String, Integer>> getGameSubsets(String game) {
        List<Map<String, Integer>> gameSubsets = new ArrayList<>();

        for (String subset : game.split(UnicodeConst.COLON_SPACE)[1].split(UnicodeConst.SEMICOLON_SPACE)) {
            String[] subsetElements = subset.split(UnicodeConst.COMMA_SPACE);
            Map<String, Integer> subsetColors = new HashMap<>();
            subsetColors.put(RED_KEY, 0);
            subsetColors.put(GREEN_KEY, 0);
            subsetColors.put(BLUE_KEY, 0);
            gameSubsets.add(subsetColors);

            for (String element : subsetElements) {
                String[] elementProps = element.split(UnicodeConst.SPACE);
                int amount = Integer.parseInt(elementProps[0]);
                String color = elementProps[1];
                subsetColors.put(color, amount);
            }
        }

        return gameSubsets;
    }

    private int getGameIndex(String game) {
        return Integer.parseInt(game.split(UnicodeConst.COLON)[0].substring("Game ".length()));
    }
}
