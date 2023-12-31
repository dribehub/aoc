package model;

import base.GlobalVariable;
import io.PathFormat;
import io.Reader;
import utils.PrintUtils;

/**
 * Class that provides ASCII art for CLI output.
 */
public class AsciiArt extends GlobalVariable {

    private final int year;
    private final String[] lockedAsciiLines;

    public AsciiArt(int year) {
        this.year = year;
        this.lockedAsciiLines = Reader.readAsStrings(
                PathFormat.getAsciiArtPath(this.year)
        );
    }

    public AsciiArt() {
        this.year = GlobalVariable.CURRENT_YEAR;
        this.lockedAsciiLines = Reader.readAsStrings(
                PathFormat.getAsciiArtPath(this.year)
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
        for (int i = this.lockedAsciiLines.length; i > 0; i--) {
            Day day = DAY_PROVIDER.fetchInstanceByYearAndDay(this.year, i);

            if (day == null || day.getAsciiLine() == null) {
                this.printLockedLineByIndex(i);
            } else {
                day.printAsciiLine();
            }
        }
    }
}
