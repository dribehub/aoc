import base.GlobalVariable;
import model.AsciiArt;
import model.Day;
import reflection.ClassProvider;

import java.util.stream.IntStream;

public class Main extends GlobalVariable {

    public static void main(String[] args) {
        GlobalVariable.CURRENT_YEAR = 2023;
        GlobalVariable.DAY_PROVIDER = new ClassProvider<>();
        GlobalVariable.ASCII_ART = new AsciiArt();

        GlobalVariable.ASCII_ART.printCurrentYear();
        Main.runAdvent();
    }

    /**
     * Runs a specific day of the year.
     * @param currentDay an integer between 1 and 25
     */
    private static void runDay(int currentDay) {
        Day day = DAY_PROVIDER.fetchInstanceByYearAndDay(CURRENT_YEAR, currentDay);
        if (day != null) {
            day.run();
        }
    }

    /**
     * Runs all days of the year.
     */
    private static void runAdvent() {
        IntStream.rangeClosed(1, 25).forEach(Main::runDay);
    }
}
