package com.yevheniimakar.module1.level1.task1;

import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;
import com.yevheniimakar.module1.console.ConsoleReader;

@Task(taskName = "Unique numbers", order = 1)
public class UniqueNumbers {

    ConsoleReader consoleReader = new ConsoleReader();

    public int getNumberOfUniqueNumbers(int[] numArr) {
        int result = 0;
        String numberToString = "";
        for (int number : numArr
        ) {
            numberToString = numberToString + number + " ";
        }
        String[] numberToStringArr = numberToString.split(" ");

        while (!numberToString.equals("")) {
            numberToString = numberToString.replaceAll(numberToStringArr[0] + " ", "");

            numberToStringArr = numberToString.split(" ");
            result += 1;
        }
        return result;

    }

    @RunTask(runTaskName = "Unique numbers")
    public void startTask(){
        System.out.println("Your choice uniqueNumbers from arr");
        boolean isContinue = true;
        while (isContinue) {
            System.out.print("enter numbers separated by a space: ");
            String numberString = consoleReader.getStringFromConsole().trim().replaceAll(" {1,}", " ").replaceAll("[^0-9 ]", "");
            String[] numberArr = numberString.split("[ \\t\\n\\x0B\\f\\r]");
            if (numberArr.length > 0) {
                int[] numArr = new int[numberArr.length];
                for (int i = 0; i < numArr.length; i++) {
                    numArr[i] = Integer.parseInt(numberArr[i]);
                }
                System.out.println(new UniqueNumbers().getNumberOfUniqueNumbers(numArr));
                isContinue = false;
                break;
            }
            System.out.println("You entered wrong data");
        }
    }




}
