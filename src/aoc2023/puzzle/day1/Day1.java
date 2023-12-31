package aoc2023.puzzle.day1;

import io.Reader;
import model.Day;

import java.util.*;

public class Day1 extends Day {

    private final List<String> input;

    public Day1() {
        super("Trebuchet?!");
        this.input = Reader.readAsList(getInputPath());
        super.setAsciiLine("  * ! /^\\                                          ");
    }

    /**
     * You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending
     * you ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just
     * say the sky ("of course, where do you think snow comes from") when you realize that the Elves are already loading
     * you into a trebuchet ("please hold still, we need to strap you in").
     * <br><br>
     * As they're making the final adjustments, they discover that their calibration document (your puzzle input) has
     * been <b>amended</b> by a very young Elf who was apparently just excited to show off her art skills.
     * Consequently, the Elves are having trouble reading the values on the document.
     * <br><br>
     * The newly-improved calibration document consists of lines of text; each line originally contained a specific
     * <b>calibration value</b> that the Elves now need to recover. On each line, the calibration value can be found by
     * combining the <b>first digit</b> and the <b>last digit</b> (in that order) to form a single <b>two-digit
     * number</b>.
     * <br><br>
     * <b>What is the sum of all of the calibration values?</b>
     */
    @Override
    public void solveLvl1() {
        int count = 0;

        for (String line : input) {
            String calibrationValue = this.findFirstNumeric(line);
            String reversed = new StringBuilder(line).reverse().toString();
            calibrationValue += this.findFirstNumeric(reversed);
            count += Integer.parseInt(calibrationValue);
        }

        super.setLvl1Answer(count); // 55488
    }

    private String findFirstNumeric(String str) {
        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                return String.valueOf(ch);
            }
        }

        return null;
    }

    /**
     * Your calculation isn't quite right. It looks like some of the digits are actually <b>spelled out with
     * letters</b>: one, two, three, four, five, six, seven, eight, and nine <b>also</b> count as valid "digits".
     * <br><br>
     * Equipped with this new information, you now need to find the real first and last digit on each line.
     * <br><br>
     * <b>What is the sum of all of the calibration values?</b>
     */
    @Override
    public void solveLvl2() {
        int count = 0;

        for (String line : input) {
            Map<Integer, String> foundDigits = new HashMap<>();

            char[] charArray = line.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                if (Character.isDigit(charArray[i])) {
                    foundDigits.put(i, String.valueOf(charArray[i]));
                }
            }

            foundDigits.putAll(this.findFirstAndLastLiteralIfAny(line));

            int first = Collections.min(foundDigits.keySet());
            int last = Collections.max(foundDigits.keySet());
            String calibrationValue = foundDigits.get(first) + foundDigits.get(last);
            count += Integer.parseInt(calibrationValue);
        }

        super.setLvl2Answer(count); // 55614
    }

    private Map<Integer, String> findFirstAndLastLiteralIfAny(String str) {
        int firstLiteralIndex = str.length();
        int lastLiteralIndex = 0;
        Integer firstLiteralValue = null;
        Integer lastLiteralValue = null;

        for (String val : LiteralDigit.getAllLiterals()) {
            if (str.contains(val)) {
                if (str.indexOf(val) < firstLiteralIndex) {
                    firstLiteralIndex = str.indexOf(val);
                    firstLiteralValue = LiteralDigit.getByLiteral(val).getNumeric();
                }

                if (str.lastIndexOf(val) > lastLiteralIndex) {
                    lastLiteralIndex = str.lastIndexOf(val);
                    lastLiteralValue = LiteralDigit.getByLiteral(val).getNumeric();
                }
            }
        }

        Map<Integer, String> firstAndLastLiteral = new HashMap<>();

        if (firstLiteralValue != null) {
            firstAndLastLiteral.put(firstLiteralIndex, String.valueOf(firstLiteralValue));
        }

        if (lastLiteralValue != null) {
            firstAndLastLiteral.put(lastLiteralIndex, String.valueOf(lastLiteralValue));
        }

        return firstAndLastLiteral;
    }
}
