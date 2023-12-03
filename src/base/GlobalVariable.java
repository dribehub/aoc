package base;

import model.AsciiArt;
import model.Day;
import reflection.ClassProvider;

/**
 * Class that provides static instances of global objects when extended,
 * implementing this way a singleton pattern use.
 */
public class GlobalVariable {

    /**
     * Global variable that sets the current year.
     */
    public static int CURRENT_YEAR;

    /**
     * Global variable that sets the {@link AsciiArt} instance.
     */
    public static AsciiArt ASCII_ART;

    /**
     * Global variable that sets the {@link ClassProvider}<{@link Day}> instance.
     */
    public static ClassProvider<Day> DAY_PROVIDER;
}
