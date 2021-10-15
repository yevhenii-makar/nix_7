package com.yevheniimakar.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {
    public static String getStringFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static Long getLongFromConsole() throws NumberFormatException{

        Long value = Long.parseLong(getStringFromConsole().trim());

        return value;

    }
}
