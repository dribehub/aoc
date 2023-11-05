package model;

import utils.PrintUtils;

public abstract class Day {

    public static final String FILE_NAME_FORMAT = "day%d.txt";
    public static final String INPUT_PATH_FORMAT = "src/aoc%d/input/%s";
    public static final String DEMO_INPUT_PATH_FORMAT = "src/aoc%d/input/demo/%s";

    private final int year;
    private final int index;
    private final String name;

    private final String inputPath;
    private final String demoInputPath;

    private final Level lvl1;
    private final Level lvl2;

    private final AsciiArt asciiArt;
    private String asciiLine;
    private boolean isFinished;

    public Day(String name) {
        this.year = Integer.parseInt(this.getClass().getPackageName().substring(3, 7));
        this.index = Integer.parseInt(this.getClass().getSimpleName().substring(3));
        this.name = name;

        String fileName = String.format(FILE_NAME_FORMAT, this.index);
        this.inputPath = String.format(INPUT_PATH_FORMAT, this.year, fileName);
        this.demoInputPath = String.format(DEMO_INPUT_PATH_FORMAT, this.year, fileName);

        this.lvl1 = new Level(1);
        this.lvl2 = new Level(2);

        this.asciiArt = new AsciiArt(this.year);
    }

    public abstract void solveLvl1();

    public abstract void solveLvl2();

    public void run() {
        this.solveLvl1();
        this.solveLvl2();
        PrintUtils.printDay(this);
        PrintUtils.printLevel(lvl1);
        PrintUtils.printLevel(lvl2);
    }

    public int getYear() {
        return year;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getInputPath() {
        return inputPath;
    }

    public String getDemoInputPath() {
        return demoInputPath;
    }

    public Level getLvl1() {
        return lvl1;
    }

    public void setLvl1Answer(Object answer) {
        this.lvl1.setAnswer(answer);
    }

    public Level getLvl2() {
        return lvl2;
    }

    public void setLvl2Answer(Object answer) {
        this.lvl2.setAnswer(answer);
    }

    public AsciiArt getAsciiArt() {
        return asciiArt;
    }

    public String getAsciiLine() {
        return asciiLine;
    }

    public void setAsciiLine(String asciiLine) {
        this.asciiLine = asciiLine;
        this.isFinished = true;
    }

    public void printAsciiLine() {
        PrintUtils.printAscii(this.year, asciiLine);
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}