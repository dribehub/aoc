package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reader {

    /**
     * Reads a specified file line by line.
     * @param filePath the path of the file to read
     * @return A list of strings containing every line of the file
     * @see BufferedReader#readLine()
     */
    public static List<String> readAsList(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines().collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Reads a specified file line by line.
     * @param filePath the path of the file to read
     * @return A string array containing every line of the file
     * @see BufferedReader#readLine()
     */
    public static String[] readAsStrings(String filePath) {
        return readAsList(filePath).toArray(new String[0]);
    }

    public static Integer[] readAsInts(String filePath) {
        try {
            return readAsList(filePath).stream().map(Integer::parseInt).toArray(Integer[]::new);
        } catch (NumberFormatException e) {
            System.out.printf("Cannot convert input to Integer[] (%s)\n", e.getMessage());
            return null;
        }
    }

    public static Integer[][] readAsMatrix(String filePath) {
        String[] strings = readAsStrings(filePath);
        int len = strings.length, wid = strings[0].length();
        Integer[][] matrix = new Integer[len][wid];
        for (int i = 0; i < len; i++) {
            String number = strings[i];
            for (int j = 0; j < wid; j++) {
                matrix[i][j] = (int) number.charAt(j);
            }
        }
        return matrix;
    }

    /**
     * Checks if a specified file can be found.
     *
     * @param   filePath the name of the file to find.
     *
     * @return  true if file is found,
     *          false  if the named file does not exist,
     *          is a directory rather than a regular file,
     *          or for some other reason cannot be opened for reading.
     * @see FileNotFoundException
     */
    public static boolean found(String filePath) {
        try {
            new FileReader(filePath);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
}