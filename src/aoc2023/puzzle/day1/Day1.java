package aoc2023.puzzle.day1;

import io.Reader;
import model.Day;

import java.util.*;

public class Day1 extends Day {

    private final List<String> input;

    public Day1() {
        super("Trebuchet?!");
        this.input = Reader.readAsList(getInputPath());
    }

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

    @Override
    public void solveLvl2() {
        int count = 0;

        // Demo input: 281 (correct)
//        List<String> input = Reader.readAsList(getDemoInputPath());

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

        // your answer is too high (55639)
        super.setLvl2Answer(count);
    }

    private Map<Integer, String> findFirstAndLastLiteralIfAny(String str) {
        int firstLiteralIndex = str.length();
        int lastLiteralIndex = 0;
        Integer firstLiteralValue = null;
        Integer lastLiteralValue = null;

        for (String val : LiteralDigit.getAllLiterals()) {
            if (str.contains(val)) {
                int index = str.indexOf(val);

                if (index < firstLiteralIndex) {
                    firstLiteralIndex = index;
                    firstLiteralValue = LiteralDigit.getByLiteral(val).getNumeric();
                }

                if (index > lastLiteralIndex) {
                    lastLiteralIndex = index;
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
