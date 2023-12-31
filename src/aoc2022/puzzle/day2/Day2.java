package aoc2022.puzzle.day2;

import io.Reader;
import model.Day;

import java.util.List;

public class Day2 extends Day {

    private final List<String> input;

    public Day2() {
        super("Rock Paper Scissors");
        this.input = Reader.readAsList(getInputPath());
        super.setAsciiLine("-~------'    ~    ~ '--~-----~-~----___________--  ");
    }

    @Override
    public void solveLvl1() {
        long score = 0;

        for (String round : input) {
            Shape opponent = Shape.findByOpponent(round.substring(0, 1));
            Shape response = Shape.findByResponse(round.substring(2, 3));
            Outcome outcome = Outcome.findOutcome(opponent, response);
            score += response.score;
            score += outcome.score;
        }

        // 12645
        super.setLvl1Answer(score);
    }

    @Override
    public void solveLvl2() {
        long score = 0;

        for (String round : input) {
            Shape opponent = Shape.findByOpponent(round.substring(0, 1));
            Outcome outcome = Outcome.findBySymbol(round.substring(2, 3));
            Shape response = opponent.findByOutcome(outcome);
            score += response.score;
            score += outcome.score;
        }

        // 11756
        super.setLvl2Answer(score);
    }
}
