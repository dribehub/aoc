package aoc2022.puzzle.day4;

import io.Reader;
import model.Day;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Day4 extends Day {

    private final List<String> input;

    public Day4() {
        super("Camp Cleanup");
        this.input = Reader.readAsList(getInputPath());
        super.setAsciiLine("@@#@##@@@@@.'~'./\\'./\\' .@#@@#@@##@@@@##@#@@#@");
    }

    @Override
    public void solveLvl1() {
        Predicate<String> predicate = line -> fullyOverlap(getIntervals(line));
        long count = input.stream().filter(predicate).count();
        super.setLvl1Answer(count); // 524
    }

    @Override
    public void solveLvl2() {
        Predicate<String> predicate = line -> this.overlap(getIntervals(line));
        long count = input.stream().filter(predicate).count();
        super.setLvl2Answer(count); // 798
    }

    private static Integer[][] getIntervals(String line) {
        String[] split = line.split(",");
        Integer[][] ranges = new Integer[2][2];

        for (int i = 0; i < ranges.length; i++) {
            String[] bounds = split[i].split("-");
            ranges[i] = Arrays.stream(bounds)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
        }

        return ranges;
    }

    private static boolean fullyOverlap(Integer[][] ranges) {
        boolean isFirstRangeMax = ranges[0][1] - ranges[0][0] > ranges[1][1] - ranges[1][0];
        Integer[] maxRange = isFirstRangeMax ? ranges[0] : ranges[1];
        Integer[] minRange = isFirstRangeMax ? ranges[1] : ranges[0];
        return maxRange[0] <= minRange[0] && minRange[1] <= maxRange[1];
    }

    private boolean overlap(Integer[][] ranges) {
        return ranges[0][0] <= ranges[1][0] && ranges[1][0] <= ranges[0][1]
            || ranges[1][0] <= ranges[0][0] && ranges[0][0] <= ranges[1][1];
    }
}
