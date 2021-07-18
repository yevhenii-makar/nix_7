package com.yevheniimakar.unit3.quantity_letter_from_string;

public class QuantityLetterFromString {

    public String countAllLettersFromString(String s) {
        String stringWithLetter = s.replaceAll("[^A-Za-z]", "").toLowerCase();
        String result = "String contains:\n";

        while (stringWithLetter.length() != 0) {
            char letter = stringWithLetter.charAt(0);
            result = result + letter + " = "
                    + countLetter(stringWithLetter, letter) + "\n";
            stringWithLetter = stringWithLetter.replaceAll("" + letter, "");

        }
        return result;

    }

    public int countLetter(String string, char letter) {
        int count = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == letter) {
                count += 1;
            }
        }
        return count;
    }
}
