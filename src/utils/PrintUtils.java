package utils;

import model.Day;
import model.Level;

import static utils.UnicodeConst.*;

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

        public String getCode() {
            return code;
        }
    }

    private static final String SEPARATOR = COLON_SPACE;

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
        print(key, value, SEPARATOR);
    }

    public static void println(String key, Object value) {
        println(key, value, SEPARATOR);
    }

    public static void print(String key, Object value, ANSI keyColor) {
        String k = paint(key, keyColor);
        print(k, value, SEPARATOR);
    }

    public static void println(String key, Object value, ANSI keyColor) {
        String k = paint(key, keyColor);
        println(k, value, SEPARATOR);
    }

    public static void print(String key, Object value, ANSI keyColor, ANSI valueColor) {
        String k = paint(key, keyColor);
        String v = paint(value.toString(), valueColor);
        print(k, v, SEPARATOR);
    }

    public static void println(String key, Object value, ANSI keyColor, ANSI valueColor) {
        String k = paint(key, keyColor);
        String v = paint(value.toString(), valueColor);
        println(k, v, SEPARATOR);
    }

    public static void print(String key, Object value, ANSI keyColor, ANSI valueColor, ANSI separatorColor) {
        String k = paint(key, keyColor);
        String v = paint(value.toString(), valueColor);
        String s = paint(SEPARATOR, separatorColor);
        print(k, v, s);
    }

    public static void println(String key, Object value, ANSI keyColor, ANSI valueColor, ANSI separatorColor) {
        String k = paint(key, keyColor);
        String v = paint(value.toString(), valueColor);
        String s = paint(SEPARATOR, separatorColor);
        println(k, v, s);
    }

    public static void printDay(Day day) {
        String str = String.format("--- Day %d: %s ---", day.getIndex(), day.getName());

        if (day.isCompleted()) {
            str = paint(str, Day.COMPLETED_COLOR);
        } else if (day.isInProgress()) {
            str = paint(str, Day.IN_PROGRESS_COLOR);
        }

        System.out.println(str);
    }

    public static void printLevel(Level level) {
        String key = "\tLevel " + level.getIndex();

        if (level.getAnswer() == null) {
            println(key, "null", Level.COLOR, ANSI.GRAY);
        } else if (level.isAnswerValid()) {
            println(paint(key, Level.COLOR), level.getAnswer());
        } else {
            final String message = level.getInvalidAttempt().getState().getMessage();
            final String value = String.join(" ", level.getAnswer(), paint("(%s)".formatted(message), ANSI.RED));
            println(paint(key, Level.COLOR), value);
        }
    }

    public static void printAscii(int year, String str) {
        if (year == 2022) {
            str = paintChars(str, ANSI.GRAY, AT, HASH, PIPE);
            str = paintChars(str, ANSI.YELLOW, MINUS, UNDERSCORE, APOSTROPHE, DOT, SLASH, BACKSLASH);
            str = paintChars(str, ANSI.CYAN, TILDE);
        } else if (year == 2023) {
            str = paintChars(str, ANSI.YELLOW, ASTERISK);
            str = paintChars(str, ANSI.RED, EXCLAMATION_MARK, SLASH, BACKSLASH, CARET, MINUS, AT);
        } else {
            str = paintChars(str, ANSI.GRAY, AOC_SYMBOLS);
        }

        System.out.println(str);
    }

    private static String paint(String str, ANSI color) {
        return color.getCode() + str + ANSI.NORMAL.getCode();
    }

    private static String paintChars(String str, ANSI color, String... charSequence) {
        for (String charString : charSequence) {
            str = str.replace(charString, paint(charString, color));
        }

        return str;
    }
}
