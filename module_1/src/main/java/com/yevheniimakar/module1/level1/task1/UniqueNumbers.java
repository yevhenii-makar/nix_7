package com.yevheniimakar.module1.level1.task1;

public class UniqueNumbers {

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


}
