package com.yevheniimakar.unit3.number_from_string;

public class NumberFromString {

    public int getSumNumberFromString(String s) {

        String stringWithoutChar = s.replaceAll("\\D", "");
        int result = 0;

        for (int i = 0; i < stringWithoutChar.length(); i++) {
            result += Integer.parseInt("" + stringWithoutChar.charAt(i));
        }

        return result;
    }

}
