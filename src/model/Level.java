package model;

import utils.PrintUtils.ANSI;

import java.util.ArrayList;
import java.util.List;

public class Level {

    public static final ANSI COLOR = ANSI.BLUE;

    private final int index;
    private String answer;
    private List<Attempt> attempts;

    public Level(int index) {
        this.index = index;
        this.attempts = new ArrayList<>();
    }

    public int getIndex() {
        return index;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer == null ? null : String.valueOf(answer);
    }

    public boolean isCompleted() {
        return answer != null;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(Attempt... attempts) {
        this.attempts = List.of(attempts);
    }

    public boolean isAnswerValid() {
        return attempts.stream().allMatch(
                attempt -> attempt.isValid(
                        Long.parseLong(this.answer)
                )
        );
    }

    public Attempt getInvalidAttempt() {
        return attempts.stream().filter(
                attempt -> !attempt.isValid(
                        Long.parseLong(this.answer)
                )
        ).findFirst().orElse(null);
    }
}
