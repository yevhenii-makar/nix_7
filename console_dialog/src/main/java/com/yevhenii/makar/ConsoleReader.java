package com.yevhenii.makar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public  class ConsoleReader {
    public static int integerReader(){
        String choice = ConsoleReader.getStringFromConsole().replaceAll("[ \\t\\n\\x0B\\f\\r]", "").replace("[^0-9]", "");
        if (choice.length()>0){
            return Integer.parseInt(choice);
        }
        return 0;

    }

    public static String getStringFromConsole() {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
