package model;

import io.PathFormat;
import utils.PrintUtils;
import utils.UnicodeConst;

import static utils.PrintUtils.*;

public abstract class Day {

    public static final ANSI IN_PROGRESS_COLOR = ANSI.NORMAL_BOLD;
    public static final ANSI COMPLETED_COLOR = ANSI.YELLOW;

    private final int year;
    private final int index;
    private final Level lvl1;
    private final Level lvl2;
    private final String name;
    private final String inputPath;
    private final String demoInputPath;
    private String asciiLine;

    public Day(String name) {
        this.year = Integer.parseInt(this.getClass().getPackageName().substring(3, 7));
        this.index = Integer.parseInt(this.getClass().getSimpleName().substring(3));
        this.lvl1 = new Level(1);
        this.lvl2 = new Level(2);
        this.name = name;
        this.inputPath = PathFormat.getInputPath(this.year, this.index);
        this.demoInputPath = PathFormat.getDemoInputPath(this.year, this.index);
    }

    public abstract void solveLvl1();

    public abstract void solveLvl2();

    public void run() {
        this.solveLvl1();
        this.solveLvl2();

        if (this.hasStarted()) {
            printDay(this);
            printLevel(this.lvl1);
            printLevel(this.lvl2);
        }
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
        lvl1.setAnswer(answer);
    }

    public Level getLvl2() {
        return lvl2;
    }

    public void setLvl2Answer(Object answer) {
        lvl2.setAnswer(answer);
    }

    public String getAsciiLine() {
        return asciiLine;
    }

    public void setAsciiLine(String asciiLine) {
        this.asciiLine = asciiLine + UnicodeConst.SPACE + this.index;
    }

    public void printAsciiLine() {
        PrintUtils.printAscii(this.year, this.asciiLine);
    }

    public boolean hasStarted() {
        return lvl1.isCompleted();
    }

    public boolean isInProgress() {
        return lvl1.isCompleted() && !lvl2.isCompleted();
    }

    public boolean isCompleted() {
        return lvl1.isCompleted() && lvl2.isCompleted();
    }
}
