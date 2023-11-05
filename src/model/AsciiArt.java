package model;

import base.BaseProperties;
import io.Reader;
import utils.PrintUtils;

public class AsciiArt extends BaseProperties {

    public static final String ASCII_ART_PATH_FORMAT = "src/aoc%d/locked-ascii-art.txt";

    private final int year;
    private final String[] lockedAsciiLines;

    public AsciiArt(int year) {
        this.year = year;
        this.lockedAsciiLines = Reader.readAsStrings(
                String.format(ASCII_ART_PATH_FORMAT, this.year)
        );
    }

    public String getLockedAsciiLineByIndex(int index) {
        return index >= 1 && index <= this.lockedAsciiLines.length
                ? this.lockedAsciiLines[this.lockedAsciiLines.length - index]
                : null;
    }

    private void printLockedAsciiLineByIndex(int index) {
        PrintUtils.printAscii(this.year, this.getLockedAsciiLineByIndex(index));
    }

    public void printAsciiLine(Day day, int index) {
        if (day == null || day.getAsciiLine() == null) {
            this.printLockedAsciiLineByIndex(index);
        } else {
            day.printAsciiLine();
        }
    }

    public void generateAsciiArt() {
        switch (this.year) {
            case 2021 -> generateAsciiArt2021();
            case 2022 -> generateAsciiArt2022();
            case 2023 -> generateAsciiArt2023();
        }
    }

    private void generateAsciiArt2021() {
        for (int i = 25; i > 0; i--) {
            Day day = DAY_PROVIDER.fetchInstanceByYearAndDay(this.year, i);
            this.printAsciiLine(day, i);
        }
    }

    private void generateAsciiArt2022() {
        for (int i = 27; i >= 1; i--) {
            Day day = DAY_PROVIDER.fetchInstanceByYearAndDay(this.year, i);
            this.printAsciiLine(day, i);
        }
    }

    private void generateAsciiArt2023() {

    }
}
