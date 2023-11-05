import base.BaseProperties;
import model.AsciiArt;
import model.Day;
import reflection.ClassProvider;

import java.util.stream.IntStream;

public class Main extends BaseProperties {

    public static void main(String[] args) {
        BaseProperties.DAY_PROVIDER = ClassProvider.getInstance();
        BaseProperties.CURRENT_YEAR = 2022;
        BaseProperties.ASCII_ART = new AsciiArt(CURRENT_YEAR);
        BaseProperties.ASCII_ART.generateAsciiArt();
        Main.run();
    }

    private static void run(int currentDay) {
        Day day = DAY_PROVIDER.fetchInstanceByYearAndDay(CURRENT_YEAR, currentDay);
        if (day != null) {
            day.run();
        }
    }

    private static void run() {
        IntStream.rangeClosed(1, 25).forEach(Main::run);
    }
}
