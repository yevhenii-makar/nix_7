package com.yevheniimakar.date.controller;

import com.yevhenii.makar.ConsoleReader;
import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;
import com.yevheniimakar.date.object.CustomDateObject;
import com.yevheniimakar.date.service.CustomDateFormatter;
import com.yevheniimakar.date.service.CustomDateService;
import com.yevheniimakar.date.service.impl.CustomDateFormatterImpl;
import com.yevheniimakar.date.service.impl.CustomDateServiceImpl;
import com.yevheniimakar.exceptions.WrongDataException;
import com.yevheniimakar.exceptions.WrongFormatException;


@Task (taskName = "work with input / output format", order = 1)
public class CustomDateFormatController {

    CustomDateFormatter customDateFormatter = new CustomDateFormatterImpl();
    CustomDateService customDateService = new CustomDateServiceImpl();

    @RunTask (runTaskName = "Set date and get result", order = 1)
    public void setDateAndGetResult() {
        System.out.println("set input date or 1 to exit");
        System.out.println("date would be appropriate " + this.customDateFormatter.getInputFormat());
        final String date = ConsoleReader.getStringFromConsole();

        if (!date.trim().equalsIgnoreCase("1")) {
            try {
                final CustomDateObject customDateObject = this.customDateService.createCustomDateObject(date);
                System.out.println("You write: " + date);
                System.out.println(this.customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            } catch (WrongDataException | WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.setFormatOutputFormat();
            }
        }
    }

    @RunTask (runTaskName = "set input format", order = 2)
    public void setFormatInputFormat() {
        System.out.println("set input format or 1 to exit");
        final String format = ConsoleReader.getStringFromConsole();

        if (!format.trim().equalsIgnoreCase("1")) {
            System.out.println("You write: " + format);
            try {
                this.customDateFormatter.setInputFormat(format);
            } catch (WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.setFormatInputFormat();
            }
        }
    }

    @RunTask (runTaskName = "Set output format", order = 3)
    public void setFormatOutputFormat() {
        System.out.println("set output format or 1 to exit");
        final String format = ConsoleReader.getStringFromConsole();

        if (!format.trim().equalsIgnoreCase("1")) {
            System.out.println("You write: " + format);
            try {
                this.customDateFormatter.setOutputFormat(format);
            } catch (WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.setFormatOutputFormat();
            }
        }
    }

}
