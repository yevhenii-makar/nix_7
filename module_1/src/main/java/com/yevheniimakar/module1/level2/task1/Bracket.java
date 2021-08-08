package com.yevheniimakar.module1.level2.task1;

import com.yevhenii.makar.ConsoleReader;
import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;


@Task(taskName = "Bracket", order = 4)
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

    @RunTask(runTaskName = "Bracket")
    public void startBracket (){
        int coordinateCY;
        boolean isContinue = true;
        while (isContinue) {
            System.out.print("enter string use only bracket  (){}[]: ");
            String stringBracket = ConsoleReader.getStringFromConsole().replaceAll("[^\\{\\}\\(\\)\\(\\)]", "");
            if (stringBracket.length() > 0) {

                System.out.println("Braket string is " + (new Bracket().isBracketStringValid(stringBracket) ? " valid" : "not valid"));
                isContinue = false;
                break;
            }
            System.out.println("You entered wrong data");
        }
    }
}
