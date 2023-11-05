package aoc2022.puzzle.day2;

import java.util.Arrays;

enum Outcome {
    LOSE(0, "X"),
    DRAW(3, "Y"),
    WIN(6, "Z");

    final int score;
    final String symbol;

    Outcome(int score, String symbol) {
        this.score = score;
        this.symbol = symbol;
    }

    static Outcome findOutcome(Shape opponent, Shape response) {
        if (opponent.equals(response)) {
            return DRAW;
        } else if (opponent.getWinningResponse().equals(response)) {
            return WIN;
        } else {
            return LOSE;
        }
    }

    static Outcome findBySymbol(String symbol) {
        return Arrays.stream(values())
                .filter(o -> o.symbol.equals(symbol))
                .findFirst().orElse(null);
    }
}
