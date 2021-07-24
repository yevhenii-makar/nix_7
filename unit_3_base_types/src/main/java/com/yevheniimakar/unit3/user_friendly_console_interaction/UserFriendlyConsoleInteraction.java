package com.yevheniimakar.unit3.user_friendly_console_interaction;

import com.yevheniimakar.unit3.end_of_lessons.EndOfLessons;
import com.yevheniimakar.unit3.number_from_string.NumberFromString;
import com.yevheniimakar.unit3.quantity_letter_from_string.QuantityLetterFromString;

import java.util.Scanner;

public class UserFriendlyConsoleInteraction {
    NumberFromString numberFromString = new NumberFromString();
    QuantityLetterFromString quantityLetterFromString = new QuantityLetterFromString();
    EndOfLessons endOfLessons = new EndOfLessons();
    Scanner scanner = new Scanner(System.in);

    public void start() {

        boolean isExit = false;

        while (!isExit) {
            System.out.println("Present 1 if you wont to know count sum of number from string");
            System.out.println("Present 2 if you wont count letters from string");
            System.out.println("Present 3 if you wont to know when lessons and");
            System.out.println("Present 4 if you wont exit");
            System.out.print("Make your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Уou entered - " + scanner.next() + ". incorrect data. try again");
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("If you wont to know count sum of number from string?");
                    boolean isFirstTaskExit = false;
                    while (!isFirstTaskExit) {
                        System.out.print("please enter the string:");
                        String string = scanner.next();
                        System.out.println("Sum number from string: " + numberFromString.getSumNumberFromString(string));
                        System.out.println("Press any key for repeat or 1 to exit");

                        isFirstTaskExit = isExit();
                    }
                    break;

                case 2:
                    System.out.println("If you wont to know count  letters from string?");
                    boolean isSecondTaskExit = false;
                    while (!isSecondTaskExit) {
                        System.out.print("please enter the string:");
                        String string = scanner.next();
                        System.out.println("Count letters from string: \n" + quantityLetterFromString.countAllLettersFromString(string));

                        isSecondTaskExit = isExit();
                    }
                    break;

                case 3:
                    System.out.println("If you wont to know  when lessons and?");
                    boolean isThirdTaskExit = false;
                    while (!isThirdTaskExit) {
                        System.out.print("please enter the numm more 0 :");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Уou entered - " + scanner.next() + ". It is incorrect data. try again");
                            continue;
                        }
                        int numberOfLessons = scanner.nextInt();
                        if (numberOfLessons <= 0) {
                            System.out.println("Уou entered - " + scanner.next() + ". It is incorrect data. try again");
                            continue;
                        }

                        System.out.println(endOfLessons.endOfLessons(numberOfLessons));

                        isThirdTaskExit = isExit();
                    }
                    break;

                case 4:
                    isExit = true;
                    break;
                default:
                    System.out.println("Уou entered incorrect data. try again");
                    break;

            }
        }
    }

    private boolean isExit() {
        System.out.println("Press any key for repeat or 1 to exit");

        if (scanner.hasNextInt()) {
            int choiceExit = scanner.nextInt();

            if (choiceExit == 1) return true;

        } else {
            String resetScanner = scanner.next();
        }
        return false;
    }
}
