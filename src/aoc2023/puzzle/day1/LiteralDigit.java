package aoc2023.puzzle.day1;

import java.util.Arrays;
import java.util.stream.Stream;

public enum LiteralDigit {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9);

    private final String literal;
    private final int numeric;

    LiteralDigit(int numeric) {
        this.literal = this.toString().toLowerCase();
        this.numeric = numeric;
    }

    public String getLiteral() {
        return literal;
    }

    public int getNumeric() {
        return numeric;
    }

    private static Stream<LiteralDigit> streamValues() {
        return Arrays.stream(LiteralDigit.values());
    }

    public static boolean isLiteral(String str) {
        return LiteralDigit.streamValues()
                .map(LiteralDigit::getLiteral)
                .anyMatch(literal -> literal.equals(str));
    }

    public static String[] getAllLiterals() {
        return LiteralDigit.streamValues()
                .map(LiteralDigit::getLiteral)
                .toArray(String[]::new);
    }

    public static LiteralDigit getByLiteral(String literal) {
        return LiteralDigit.streamValues()
                .filter(ld -> ld.literal.equals(literal))
                .findFirst().orElse(null);
    }

    public static LiteralDigit getByNumeric(int numeric) {
        return Arrays.stream(LiteralDigit.values())
                .filter(ld -> ld.numeric == numeric)
                .findFirst().orElse(null);
    }
}
