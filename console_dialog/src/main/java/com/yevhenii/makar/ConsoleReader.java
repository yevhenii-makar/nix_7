package com.yevhenii.makar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleReader {

    public static int integerReader() {
        String choice = ConsoleReader.getStringFromConsole().replaceAll("[ \\t\\n\\x0B\\f\\r]", "").replace("[^0-9]", "");
        if (choice.length() > 0) {
            return Integer.parseInt(choice);
        }
        return 0;
    }

    public static double doubleReader() {
        String choice = ConsoleReader.getStringFromConsole().replaceAll("[ \\t\\n\\x0B\\f\\r]", "").replace("[^0-9.]", "");
        if (choice.length() > 0) {
            return Double.parseDouble(choice);
        }
        return 0;
    }

    public static String getStringFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Integer[] integerArrayReader() {
        String choice = ConsoleReader.getStringFromConsole()
							.replaceAll("[\\t\\n\\x0B\\f\\r]", "").trim()
							.replaceAll("  {2,}", " ")
							.replace("[^0-9 ]", "");
        String[] integerStringArr;
        Integer[] intArray = null;

        if (choice.length() > 0) {
            integerStringArr = choice.split(" ");
            intArray = new Integer[integerStringArr.length];
            for (int i = 0; i < integerStringArr.length; i++) {
                intArray[i] = Integer.parseInt(integerStringArr[i]);
            }
        }
        return intArray;
    }

    public static Double[] doubleArrayReader() {
        String choice = ConsoleReader.getStringFromConsole()
                .replaceAll("[\\t\\n\\x0B\\f\\r]", "").trim()
                .replaceAll("  {2,}", " ")
                .replace("[^0-9. ]", "");
        String[] doubleStringArr;
        Double[] doubleArray = null;

        if (choice.length() > 0) {
            doubleStringArr = choice.split(" ");
            doubleArray = new Double[doubleStringArr.length];
            for (int i = 0; i < doubleStringArr.length; i++) {
                doubleArray[i] = Double.parseDouble(doubleStringArr[i]);
            }
        }
        return doubleArray;
    }

}
