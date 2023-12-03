package reflection;

/**
 * Class that provides other classes' instances using java reflection.
 * @param <T> the class to be fetched
 */
public class ClassProvider<T>  {

    public static final String DAY_PATH_FORMAT = "aoc%d.puzzle.day%d.Day%d";

    /**
     * Finds if a class exists by reading to its path.
     * @param path the absolute path of the class
     * @return true if exists, otherwise false
     */
    public static boolean exists(String path) {
        try {
            Class.forName(path);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Fetches the class instance if it exists.
     * @param path the absolute path of the class
     * @return the class instance if exists, otherwise null
     */
    @SuppressWarnings("unchecked")
    public T fetchInstance(String path) {
        if (exists(path)) {
            try {
                return (T) Class.forName(path).getDeclaredConstructor().newInstance();
            } catch (ReflectiveOperationException e) {
                return null;
            }
        }

        return null;
    }

    /**
     * Fetches the {@link model.Day Day} instance if it exists.
     * @param year the year of AoC
     * @param index the day of the year
     * @return the class instance if exists, otherwise null
     */
    public T fetchInstanceByYearAndDay(int year, int index) {
        String path = String.format(DAY_PATH_FORMAT, year, index, index);
        return this.fetchInstance(path);
    }
}
