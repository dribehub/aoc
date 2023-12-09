package utils;

import model.Day;
import model.Level;

public class PrintUtils {

    public enum ANSI {
        NORMAL(0, false),
        RED(31, false),
        GREEN(32, false),
        YELLOW(33, false),
        BLUE(34, false),
        PURPLE(35, false),
        CYAN(36, false),
        GRAY(37, false),
        NORMAL_BOLD(0, true),
        RED_BOLD(31, true),
        GREEN_BOLD(32, true),
        YELLOW_BOLD(33, true),
        BLUE_BOLD(34, true),
        PURPLE_BOLD(35, true),
        CYAN_BOLD(36, true),
        GRAY_BOLD(37, true);

        private final String code;

        ANSI(int code, boolean bold) {
            String boldFormat = "\u001b[%d;1m";
            String normalFormat = "\u001b[%dm";
            String format = bold ? boldFormat : normalFormat;
            this.code = String.format(format, code);
        }

        public String value() {
            return code;
        }
    }

    private static final String separator = ": ";

    private static void print(String key, Object value, String separator) {
        System.out.printf("%s%s%s", key, separator, value);
    }

    private static void println(String key, Object value, String separator) {
        System.out.printf("%s%s%s\n", key, separator, value);
    }

    public static void print(String str, ANSI color) {
        System.out.print(paint(str, color));
    }

    public static void println(String str, ANSI color) {
        System.out.println(paint(str, color));
    }

    public static void print(String key, Object value) {
        PrintUtils.print(key, value, separator);
    }

    public static void println(String key, Object value) {
        PrintUtils.println(key, value, separator);
    }

    public static void print(String key, Object value, ANSI keyColor) {
        String k = paint(key, keyColor);
        PrintUtils.print(k, value, separator);
    }

    public static void println(String key, Object value, ANSI keyColor) {
        String k = paint(key, keyColor);
        PrintUtils.println(k, value, separator);
    }

    public static void print(String key, Object value, ANSI keyColor, ANSI valueColor) {
        String k = paint(key, keyColor);
        String v = paint(value.toString(), valueColor);
        PrintUtils.print(k, v, separator);
    }

    public static void println(String key, Object value, ANSI keyColor, ANSI valueColor) {
        String k = paint(key, keyColor);
        String v = paint(value.toString(), valueColor);
        PrintUtils.println(k, v, separator);
    }

    public static void print(String key, Object value, ANSI keyColor, ANSI valueColor, ANSI separatorColor) {
        String k = paint(key, keyColor);
        String v = paint(value.toString(), valueColor);
        String s = paint(separator, separatorColor);
        PrintUtils.print(k, v, s);
    }

    public static void println(String key, Object value, ANSI keyColor, ANSI valueColor, ANSI separatorColor) {
        String k = paint(key, keyColor);
        String v = paint(value.toString(), valueColor);
        String s = paint(separator, separatorColor);
        PrintUtils.println(k, v, s);
    }

    public static void printFaded(String key, Object value) {
        PrintUtils.print(key, value, ANSI.GRAY, ANSI.GRAY, ANSI.GRAY);
    }

    public static void printlnFaded(String key, Object value) {
        PrintUtils.println(key, value, ANSI.GRAY, ANSI.GRAY, ANSI.GRAY);
    }

    public static void printDay(Day day) {
        String str = String.format("--- Day %d: %s ---", day.getIndex(), day.getName());
        System.out.println(day.isFinished() ? paint(str, ANSI.YELLOW) : str);
    }

    public static void printLevel(Level level) {
        String key = "\tLevel " + level.getIndex();
        if (level.getAnswer() == null) {
            PrintUtils.printlnFaded(key, "null");
        } else {
            PrintUtils.println(paint(key, PrintUtils.ANSI.BLUE), level.getAnswer());
        }
    }

    public static void printAscii(int year, String str) {
        if (year == 2022) {
            str = PrintUtils.paintChars(str, ANSI.GRAY, "@", "#", "|");
            str = PrintUtils.paintChars(str, ANSI.YELLOW, "-", "_", "'", ".", "/", "\\");
            str = PrintUtils.paintChars(str, ANSI.CYAN, "~");
        } else if (year == 2023) {
            str = PrintUtils.paintChars(str, ANSI.YELLOW, "*");
            str = PrintUtils.paintChars(str, ANSI.RED, "!", "/", "\\", "^", "-", "@");
        } else {
            str = PrintUtils.paintChars(str, ANSI.GRAY, "@", "#", "|", "-", "_", "'", ".", "/", "\\", "~", ":");
        }

        System.out.println(str);
    }

    private static String paint(String str, ANSI color) {
        return color.code + str + ANSI.NORMAL.code;
    }

    private static String paintChars(String str, ANSI color, String... charSequence) {
        for (String charString : charSequence) {
            str = str.replace(charString, PrintUtils.paint(charString, color));
        }

        return str;
    }
}
