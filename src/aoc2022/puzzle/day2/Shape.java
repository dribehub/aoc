package aoc2022.puzzle.day2;

import java.util.Arrays;

enum Shape {

    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3);

    final String opponent;
    final String response;
    final int score;

    Shape(String opponent, String response, int score) {
        this.opponent = opponent;
        this.response = response;
        this.score = score;
    }

    Shape getWinningResponse() {
        return switch (this) {
            case ROCK -> PAPER;
            case PAPER -> SCISSORS;
            case SCISSORS -> ROCK;
        };
    }

    Shape getLosingResponse() {
        return switch (this) {
            case ROCK -> SCISSORS;
            case PAPER -> ROCK;
            case SCISSORS -> PAPER;
        };
    }

    static Shape findByOpponent(String opponent) {
        return Arrays.stream(values())
                .filter(s -> s.opponent.equals(opponent))
                .findFirst().orElse(null);
    }

    static Shape findByResponse(String response) {
        return Arrays.stream(values())
                .filter(s -> s.response.equals(response))
                .findFirst().orElse(null);
    }

    Shape findByOutcome(Outcome outcome) {
        return switch (outcome) {
            case LOSE -> getLosingResponse();
            case DRAW -> this;
            case WIN -> getWinningResponse();
        };
    }
}
