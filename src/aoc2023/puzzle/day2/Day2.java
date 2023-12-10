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

    private final int MAX_RED_CUBES = 12;
    private final int MAX_GREEN_CUBES = 13;
    private final int MAX_BLUE_CUBES = 14;

    public Day2() {
        super("Cube Conundrum");
        this.input = Reader.readAsList(getInputPath());
    }

    @Override
    public void solveLvl1() {
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

    private int getGameIndex(String game) {
        return Integer.parseInt(game.split(UnicodeConst.COLON)[0].substring("Game ".length()));
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

    @Override
    public void solveLvl2() {
        super.setLvl2Answer(null);
    }
}
