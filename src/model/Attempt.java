package model;

public class Attempt {

    public enum State {
        TOO_HIGH("Your answer is too high"),
        TOO_LOW("Your answer is too low"),
        INCORRECT("Your answer is incorrect");

        private final String message;

        State(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    private final int answer;
    private final State state;

    private Attempt(int answer, State state) {
        this.answer = answer;
        this.state = state;
    }

    public boolean isTooHigh() {
        return State.TOO_HIGH.equals(this.state);
    }

    public boolean isTooLow() {
        return State.TOO_LOW.equals(this.state);
    }

    public boolean isIncorrect() {
        return State.INCORRECT.equals(this.state);
    }

    public boolean isValid(long answer) {
        return switch (this.state) {
            case TOO_LOW -> answer > this.answer;
            case TOO_HIGH -> answer < this.answer;
            case INCORRECT -> answer != this.answer;
        };
    }

    public int getAnswer() {
        return answer;
    }

    public State getState() {
        return state;
    }

    public static Attempt tooHigh(int answer) {
        return new Attempt(answer, State.TOO_HIGH);
    }

    public static Attempt tooLow(int answer) {
        return new Attempt(answer, State.TOO_LOW);
    }

    public static Attempt incorrect(int answer) {
        return new Attempt(answer, State.INCORRECT);
    }
}
