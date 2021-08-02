package com.yevheniimakar.module1.level2.task1;

public class Bracket {

    public boolean isBracketStringValid(String bracketString) {
        boolean result = false;

        bracketString = bracketString.replaceAll("[ \\t\\n\\x0B\\f\\r]", "");

        if (bracketString.equals("")) {
            result = true;
        }
        if (bracketString.contains("{}") || bracketString.contains("()") || bracketString.contains("[]")) {
            bracketString = bracketString.replaceAll("\\{\\}", "");
            bracketString = bracketString.replaceAll("\\(\\)", "");
            bracketString = bracketString.replaceAll("\\(\\)", "");
            result = isBracketStringValid(bracketString);
        }
        if (!(bracketString.contains("{}") || bracketString.contains("()") || bracketString.contains("[]"))
                && bracketString.length() > 0) {
            result = false;
        }
        return result;
    }
}
