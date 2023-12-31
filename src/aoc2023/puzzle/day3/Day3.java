package aoc2023.puzzle.day3;

import io.Reader;
import model.Attempt;
import model.Day;
import utils.UnicodeConst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3 extends Day {

    private final char[][] input;

    private final String[] symbols = {
            UnicodeConst.AT,
            UnicodeConst.HASH,
            UnicodeConst.DOLLAR,
            UnicodeConst.PERCENT,
            UnicodeConst.AMPERSAND,
            UnicodeConst.ASTERISK,
            UnicodeConst.MINUS,
            UnicodeConst.PLUS,
            UnicodeConst.EQUALS,
            UnicodeConst.SLASH
    };

    private final static String GEAR = UnicodeConst.ASTERISK;

    public Day3() {
        super("Gear Ratios");
        this.input = Reader.readAsCharMatrix(getInputPath());
//        this.input = Reader.readAsCharMatrix(getDemoInputPath());
    }

    /**
     * You and the Elf eventually reach a gondola lift station; he says the gondola lift will take you up to the
     * <b>water source</b>, but this is as far as he can bring you. You go inside.
     * <br><br>
     * It doesn't take long to find the gondolas, but there seems to be a problem: they're not moving.
     * <br><br>
     * "Aaah!"
     * <br><br>You turn around to see a slightly-greasy Elf with a wrench and a look of surprise. "Sorry, I wasn't
     * expecting anyone! The gondola lift isn't working right now; it'll still be a while before I can fix it." You
     * offer to help.
     * <br><br>
     * The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which
     * one. If you can <b>add up all the part numbers</b> in the engine schematic, it should be easy to work out which
     * part is missing.
     * <br><br>
     * The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of
     * numbers and symbols you don't really understand, but apparently <b>any number adjacent to a symbol</b>, even
     * diagonally, is a "part number" and should be included in your sum. (Periods (.) do not count as a symbol.)
     * <br><br><b>What is the sum of all of the part numbers in the engine schematic?<b/>
     */
    @Override
    public void solveLvl1() {
        int sum = 0;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (Character.isDigit(input[i][j])) {
                    int partNumber = this.getPartNumber(i, j);
                    int numOfDigits = String.valueOf(partNumber).length();

                    if (this.hasAdjacentSymbol(i, j, numOfDigits)) {
                        sum += partNumber;
                    }

                    j += numOfDigits - 1;
                }
            }
        }

        super.setLvl1Answer(sum); // 532445
    }

    private boolean hasAdjacentSymbol(int x, int y, int numOfDigits) {
        boolean hasUpperBorder = x - 1 == -1;
        boolean hasLowerBorder = x + 1 == input.length;
        boolean hasLeftBorder = y - 1 == -1;
        boolean hasRightBorder = y + numOfDigits == input[0].length;

        // check upper row neighbours
        if (!hasUpperBorder) {
            // check upper left corner neighbour
            if (!hasLeftBorder && this.isSymbol(input[x - 1][y - 1])) {
                return true;
            }

            // check upper right corner neighbour
            if (!hasRightBorder && this.isSymbol(input[x - 1][y + numOfDigits])) {
                return true;
            }

            // check upper row in-between corner neighbours
            for (int i = 0; i < numOfDigits; i++) {
                if (this.isSymbol(input[x - 1][y + i])) {
                    return true;
                }
            }
        }

        // check lower row neighbours
        if (!hasLowerBorder) {
            // check lower left corner neighbour
            if (!hasLeftBorder && this.isSymbol(input[x + 1][y - 1])) {
                return true;
            }

            // check lower right corner neighbour
            if (!hasRightBorder && this.isSymbol(input[x + 1][y + numOfDigits])) {
                return true;
            }

            // check lower row in-between corner neighbours
            for (int i = 0; i < numOfDigits; i++) {
                if (this.isSymbol(input[x + 1][y + i])) {
                    return true;
                }
            }
        }

        // check left and right neighbours
        return (!hasLeftBorder && this.isSymbol(input[x][y - 1]))
                || (!hasRightBorder && this.isSymbol(input[x][y + numOfDigits]));
    }

    /**
     * The engineer finds the missing part and installs it in the engine! As the engine springs to life, you jump in the
     * closest gondola, finally ready to ascend to the water source.
     * <br><br>
     * You don't seem to be going very fast, though. Maybe something is still wrong? Fortunately, the gondola has a
     * phone labeled "help", so you pick it up and the engineer answers.
     * <br><br>
     * Before you can explain the situation, she suggests that you look out the window. There stands the engineer,
     * holding a phone in one hand and waving with the other. You're going so slowly that you haven't even left the
     * station. You exit the gondola.
     * <br><br>
     * The missing part wasn't the only issue - one of the gears in the engine is wrong. A <b>gear</b> is any * symbol
     * that is adjacent to <b>exactly two part numbers</b>. Its <b>gear ratio</b> is the result of multiplying those
     * two numbers together.
     * <br><br>
     * This time, you need to find the gear ratio of every gear and add them all up so that the engineer can figure out
     * which gear needs to be replaced.
     * <br><br>
     * <b>What is the sum of all of the gear ratios in your engine schematic?</b>
     */
    @Override
    public void solveLvl2() {
        int sum = 0;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (GEAR.equals(String.valueOf(input[i][j]))) {
                    List<Integer> adjacentPartNumbers = this.getAdjacentPartNumbers(i, j);

                    if (adjacentPartNumbers.size() == 2) {
                        sum += adjacentPartNumbers.stream().reduce(1, (a, b) -> a * b);
                    }
                }
            }
        }

        super.getLvl2().setAttempts(
                Attempt.tooLow(4154748),
                Attempt.tooHigh(88087824),
                Attempt.incorrect(46210628),
                Attempt.incorrect(62013370),
                Attempt.incorrect(72150277)
        );

        super.setLvl2Answer(sum);
    }

    private List<Integer> getAdjacentPartNumbers(int x, int y) {
        List<Integer> adjacentPartNumbers = new ArrayList<>();

        boolean hasUpperBorder = x == 0;
        boolean hasLowerBorder = x == input.length - 1;
        boolean hasLeftBorder = y == 0;
        boolean hasRightBorder = y == input[0].length - 1;

        // check upper row neighbours
        if (!hasUpperBorder) {
            boolean isLeftValueDigit = false;

            // check upper left corner neighbour
            if (!hasLeftBorder && Character.isDigit(input[x - 1][y - 1])) {
                isLeftValueDigit = true;
                adjacentPartNumbers.add(this.getPartNumber(x - 1, y - 1));
            }

            // check upper row in-between corner and upper right corner neighbours
            if (!isLeftValueDigit && Character.isDigit(input[x - 1][y])) {
                isLeftValueDigit = true;
                adjacentPartNumbers.add(this.getPartNumber(x - 1, y));
            }

            if (!hasRightBorder && !isLeftValueDigit && Character.isDigit(input[x - 1][y + 1])) {
                adjacentPartNumbers.add(this.getPartNumber(x - 1, y + 1));
            }
        }

        // check lower row neighbours
        if (!hasLowerBorder) {
            boolean isLeftValueDigit = false;

            // check lower left corner neighbour
            if (!hasLeftBorder && Character.isDigit(input[x + 1][y - 1])) {
                isLeftValueDigit = true;
                adjacentPartNumbers.add(this.getPartNumber(x + 1, y - 1));
            }

            // check lower row in-between corner and lower right corner neighbours
            if (!isLeftValueDigit && Character.isDigit(input[x + 1][y])) {
                isLeftValueDigit = true;
                adjacentPartNumbers.add(this.getPartNumber(x + 1, y));
            } else if (!hasRightBorder && !isLeftValueDigit && Character.isDigit(input[x + 1][y + 1])) {
                adjacentPartNumbers.add(this.getPartNumber(x + 1, y + 1));
            }
        }

        // check left neighbour
        if (!hasLeftBorder && Character.isDigit(input[x][y - 1])) {
            adjacentPartNumbers.add(this.getPartNumber(x, y - 1));
        }

        // check right neighbour
        if (!hasRightBorder && Character.isDigit(input[x][y + 1])) {
            adjacentPartNumbers.add(this.getPartNumber(x, y + 1));
        }

        return adjacentPartNumbers;
    }

    private boolean isSymbol(char ch) {
        return Arrays.stream(symbols).anyMatch(
                symbol -> symbol.contains(String.valueOf(ch)));
    }

    private int getPartNumber(int x, int y) {
        while (y > 0 && Character.isDigit(input[x][y - 1])) {
            y--;
        }

        StringBuilder number = new StringBuilder("" + input[x][y]);
        int numOfDigits = 1;

        while (y + numOfDigits < input[x].length && Character.isDigit(input[x][y + numOfDigits])) {
            number.append(input[x][y + numOfDigits++]);
        }

        return Integer.parseInt(number.toString());
    }
}
