package aoc2022.puzzle.day3;

import io.Reader;
import model.Day;

import java.util.List;

public class Day3 extends Day {

    private final List<String> input;

    public Day3() {
        super("Rucksack Reorganization");
        this.input = Reader.readAsList(getInputPath());
        super.setAsciiLine("@@@@##@@@_/ ~   ~  \\ ' '. '.'.@#@#@#@#@@@###@@@#@");
    }

    @Override
    public void solveLvl1() {
        long prioritiesSum = 0;

        for (String rucksack : input) {
            int middle = rucksack.length() / 2;
            String first = rucksack.substring(0, middle);
            String second = rucksack.substring(middle);
            prioritiesSum += getPriority(getCommonChar(first, second));
        }

        // 7875
        super.setLvl1Answer(prioritiesSum);
    }

    @Override
    public void solveLvl2() {
        long prioritiesSum = 0;
        int amountOfGroups = input.size() / 3;

        for (int i = 0; i < amountOfGroups; i++) {
            String[] group = input.subList(i * 3, i * 3 + 3).toArray(new String[0]);
            prioritiesSum += getPriority(getBadge(group));
        }

        // 2479
        super.setLvl2Answer(prioritiesSum);
    }

    private static int getPriority(char ch) {
        return Character.isUpperCase(ch)
                ? (int) ch - 38 
                : (int) ch - 96;
    }

    private static char getCommonChar(String s1, String s2) {
        for (char ch : s1.toCharArray()) {
            if (s2.indexOf(ch) != -1) {
                return ch;
            }
        }

        return 0;
    }

    private static char getBadge(String[] group) {
        for (char ch : group[0].toCharArray()) {
            if (group[1].indexOf(ch) != -1 && group[2].indexOf(ch) != -1) {
                return ch;
            }
        }

        return 0;
    }
}
