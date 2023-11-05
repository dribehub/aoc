package model;

public class Level {

    private final int index;

    private String answer;

    public Level(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = String.valueOf(answer);
    }
}