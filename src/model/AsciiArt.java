package model;

import base.GlobalVariable;
import io.Reader;
import utils.PrintUtils;

/**
 * Class that provides ASCII art for CLI output.
 */
public class AsciiArt extends GlobalVariable {

    public static final String ASCII_ART_PATH_FORMAT = "src/aoc%d/locked-ascii-art.txt";

    private final int year;
    private final String[] lockedAsciiLines;

    public AsciiArt(int year) {
        this.year = year;
        this.lockedAsciiLines = Reader.readAsStrings(
                String.format(ASCII_ART_PATH_FORMAT, this.year)
        );
    }

    public AsciiArt() {
        this.year = GlobalVariable.CURRENT_YEAR;
        this.lockedAsciiLines = Reader.readAsStrings(
                String.format(ASCII_ART_PATH_FORMAT, this.year)
        );
    }

    /**
     * Returns the locked ASCII line by index.
     * @param index the day index if the locked ASCII art is equal to 25
     * @return the locked ASCII line if exists, otherwise null
     */
    private String getLockedAsciiLineByIndex(int index) {
        int size = this.lockedAsciiLines.length;
        return index >= 1 && index <= size
                ? this.lockedAsciiLines[size - index]
                : null;
    }

    /**
     * Prints the locked ASCII line by index.
     * @param index the day index if the locked ASCII art is equal to 25
     */
    private void printLockedLineByIndex(int index) {
        PrintUtils.printAscii(this.year, this.getLockedAsciiLineByIndex(index));
    }

    /**
     * Prints the full ASCII art of the current year.
     * For the locked {@link Day}s, the locked ASCII line is printed instead.
     */
    public void printCurrentYear() {
        switch (this.year) {
            case 2021 -> print2021();
            case 2022 -> print2022();
            case 2023 -> print2023();
        }
    }

    /**
     * Prints the full ASCII art of 2021.
     * For the locked {@link Day}s, the locked ASCII line is printed instead.
     */
    private void print2021() {
        for (int i = 25; i > 0; i--) {
            Day day = DAY_PROVIDER.fetchInstanceByYearAndDay(this.year, i);
            if (day == null || day.getAsciiLine() == null) {
                this.printLockedLineByIndex(i);
            } else {
                day.printAsciiLine();
            }
        }
    }

    /**
     * Prints the full ASCII art of 2022.
     * For the locked {@link Day}s, the locked ASCII line is printed instead.
     */
    private void print2022() {
        for (int i = 27; i >= 1; i--) {
            Day day = DAY_PROVIDER.fetchInstanceByYearAndDay(this.year, i);
            if (day == null || day.getAsciiLine() == null) {
                this.printLockedLineByIndex(i);
            } else {
                day.printAsciiLine();
            }
        }
    }

    /**
     * Prints the full ASCII art of 2023.
     * For the locked {@link Day}s, the locked ASCII line is printed instead.
     */
    private void print2023() {
        for (int i = 25; i > 0; i--) {
            Day day = DAY_PROVIDER.fetchInstanceByYearAndDay(this.year, i);
            if (day == null || day.getAsciiLine() == null) {
                this.printLockedLineByIndex(i);
            } else {
                day.printAsciiLine();
            }
        }
    }
}
