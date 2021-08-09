package com.yevheniimakar.chess.service.impl;

import com.yevheniimakar.chess.service.ConsoleReaderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReaderServiceImpl implements ConsoleReaderService {

    @Override
    public String getStringFromConsole() {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            return reader.readLine().replaceAll("[ \\t\\n\\x0B\\f\\r]","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
