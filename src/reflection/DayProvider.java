package reflection;

import io.PathFormat;
import model.Day;

/**
 * Class that provides {@link Day} instances using java reflection.
 */
public class DayProvider {

    /**
     * Fetches the class instance if it exists.
     * @param path the absolute path of the class
     * @return the class instance if exists, otherwise null
     */
    public Day fetchInstance(String path) {
        try {
            return (Day) Class.forName(path).getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }

    /**
     * Fetches the {@link model.Day Day} instance if it exists.
     * @param year the year of AoC
     * @param day the day of the year
     * @return the class instance if exists, otherwise null
     */
    public Day fetchInstanceByYearAndDay(int year, int day) {
        return this.fetchInstance(PathFormat.getDayPath(year, day));
    }
}
