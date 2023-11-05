package reflection;

public class ClassProvider<T>  {

    public static final String DAY_PATH_FORMAT = "aoc%d.puzzle.day%d.Day%d";

    public static <T> ClassProvider<T> getInstance() {
        return new ClassProvider<>();
    }

    public static boolean exists(String path) {
        try {
            Class.forName(path);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

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

    public T fetchInstanceByYearAndDay(int year, int index) {
        String path = String.format(DAY_PATH_FORMAT, year, index, index);
        return fetchInstance(path);
    }
}
