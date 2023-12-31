package io;

public final class PathFormat {

    public static final String ASCII_ART_PATH_FORMAT = "src/aoc%d/locked-ascii-art.txt";
    public static final String INPUT_PATH_FORMAT = "src/aoc%d/input/%s";
    public static final String DEMO_INPUT_PATH_FORMAT = "src/aoc%d/input/demo/%s";

    public static final String DAY_PATH_FORMAT = "aoc%d.puzzle.day%d.Day%d";
    public static final String FILE_NAME_FORMAT = "day%d.txt";

    public static String getAsciiArtPath(int year) {
        return String.format(ASCII_ART_PATH_FORMAT, year);
    }

    public static String getInputPath(int year, String fileName) {
        return String.format(INPUT_PATH_FORMAT, year, fileName);
    }

    public static String getInputPath(int year, int day) {
        return getInputPath(year, PathFormat.getFileName(day));
    }

    public static String getDemoInputPath(int year, String fileName) {
        return String.format(DEMO_INPUT_PATH_FORMAT, year, fileName);
    }

    public static String getDemoInputPath(int year, int day) {
        return getDemoInputPath(year, PathFormat.getFileName(day));
    }

    public static String getDayPath(int year, int day) {
        return String.format(DAY_PATH_FORMAT, year, day, day);
    }

    public static String getFileName(int day) {
        return String.format(FILE_NAME_FORMAT, day);
    }
}
