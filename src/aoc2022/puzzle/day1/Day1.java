package aoc2022.puzzle.day1;

import io.Reader;
import model.Day;

import java.util.Arrays;
import java.util.List;

public class Day1 extends Day {

    private final List<String> input;

    public Day1() {
        super("Calorie Counting");
        this.input = Reader.readAsList(getInputPath());
        super.setAsciiLine("  ~    ~  ~      ~     ~ ~   ~     ~  ~  ~   ~     ");
    }

    @Override
    public void solveLvl1() {
        int maxCalories = 0;
        int currCalories = 0;

        for (String cal : input) {
            if (cal.isEmpty()) {
                maxCalories = Math.max(maxCalories, currCalories);
                currCalories = 0;
            } else {
                currCalories += Integer.parseInt(cal);
            }
        }

        // 71124
        super.setLvl1Answer(maxCalories);
    }

    @Override
    public void solveLvl2() {
        int[] maxCalories = {0, 0, 0};
        int currCalories = 0;

        for (String cal : input) {
            if (cal.isEmpty()) {
                for (int i = 0; i < 3; i++) {
                    if (maxCalories[i] < currCalories) {
                        maxCalories[i] = currCalories;
                        break;
                    }
                }
                currCalories = 0;
            } else {
                currCalories += Integer.parseInt(cal);
            }
        }

        // 204639
        super.setLvl2Answer(Arrays.stream(maxCalories).sum());
    }
}
