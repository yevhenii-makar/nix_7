package com.yevheniimakar.date.controller;

import com.yevhenii.makar.ConsoleReader;
import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;
import com.yevheniimakar.date.object.CustomDateObject;
import com.yevheniimakar.date.service.CustomDateFormatter;
import com.yevheniimakar.date.service.CustomDateService;
import com.yevheniimakar.date.service.impl.CustomDateFormatterImpl;
import com.yevheniimakar.date.service.impl.CustomDateServiceImpl;
import com.yevheniimakar.exceptions.WrongFormatException;


@Task (taskName = "work with date", order = 2)
public class CustomDateController {

    CustomDateFormatter customDateFormatter = new CustomDateFormatterImpl();
    CustomDateService customDateService = new CustomDateServiceImpl();


    @RunTask (runTaskName = "Plus years", order = 1)
    public void plusYear() {
        final String date = this.getMassage();
        final String type = "year";

        if (!date.trim().equalsIgnoreCase("1")) {
            final int num = this.getMassage(type);
            CustomDateObject customDateObject = this.customDateService.createCustomDateObject(date);
            System.out.println("You write date: " + date + " " + type + ":" + num);
            customDateObject = this.customDateService.plusYears(customDateObject, num);
            try {
                System.out.println(this.customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            } catch (final WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.plusYear();
            }
        }
    }

    @RunTask (runTaskName = "Subtract years", order = 2)
    public void subtractYear() {
        final String date = this.getMassage();
        final String type = "year";

        if (!date.trim().equalsIgnoreCase("1")) {
            final int num = this.getMassage(type);
            CustomDateObject customDateObject = this.customDateService.createCustomDateObject(date);
            System.out.println("You write date: " + date + " " + type + ":" + num);
            customDateObject = this.customDateService.subtractYears(customDateObject, num);
            try {
                System.out.println(this.customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            } catch (final WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.subtractYear();
            }
        }
    }

    @RunTask (runTaskName = "Plus month", order = 3)
    public void plusMonth() {
        final String date = this.getMassage();
        final String type = "month";
        if (!date.trim().equalsIgnoreCase("1")) {
            System.out.print("enter count years: ");
            final int num = this.getMassage(type);
            CustomDateObject customDateObject = this.customDateService.createCustomDateObject(date);
            System.out.println("You write date: " + date + " " + type + ": " + num);
            customDateObject = this.customDateService.plusMonths(customDateObject, num);
            try {
                System.out.println(this.customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            } catch (final WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.plusMonth();
            }
        }
    }


    @RunTask (runTaskName = "Subtract month", order = 4)
    public void subtractMonth() {
        final String date = this.getMassage();
        final String type = "month";
        if (!date.trim().equalsIgnoreCase("1")) {
            final int num = this.getMassage(type);
            CustomDateObject customDateObject = this.customDateService.createCustomDateObject(date);
            System.out.println("You write date: " + date + " " + type + ":" + num);
            customDateObject = this.customDateService.subtractMonths(customDateObject, num);
            try {
                System.out.println(this.customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            } catch (final WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.subtractMonth();
            }
        }
    }

    @RunTask (runTaskName = "Plus day", order = 5)
    public void plusDay() {
        final String date = this.getMassage();
        final String type = "day";
        if (!date.trim().equalsIgnoreCase("1")) {
            final int num = this.getMassage(type);
            CustomDateObject customDateObject = this.customDateService.createCustomDateObject(date);
            System.out.println("You write date: " + date + " " + type + ": " + num);
            customDateObject = this.customDateService.plusDays(customDateObject, num);
            try {
                System.out.println(this.customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            } catch (final WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.plusDay();
            }
        }
    }


    @RunTask (runTaskName = "Subtract day", order = 6)
    public void subtractDay() {
        final String date = this.getMassage();
        final String type = "day";
        if (!date.trim().equalsIgnoreCase("1")) {
            final int num = this.getMassage(type);
            CustomDateObject customDateObject = this.customDateService.createCustomDateObject(date);
            System.out.println("You write date: " + date + " " + type + ":" + num);
            customDateObject = this.customDateService.subtractDays(customDateObject, num);
            try {
                System.out.println(this.customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            } catch (final WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.subtractDay();
            }
        }
    }

    @RunTask (runTaskName = "Plus hour", order = 7)
    public void plusHour() {
        final String date = this.getMassage();
        final String type = "hour";
        if (!date.trim().equalsIgnoreCase("1")) {
            final int num = this.getMassage(type);
            CustomDateObject customDateObject = this.customDateService.createCustomDateObject(date);
            System.out.println("You write date: " + date + " " + type + ": " + num);
            customDateObject = this.customDateService.plusHours(customDateObject, num);
            try {
                System.out.println(this.customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            } catch (final WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.plusHour();
            }
        }
    }


    @RunTask (runTaskName = "Subtract hour", order = 8)
    public void subtractHour() {
        final String date = this.getMassage();
        final String type = "hour";
        if (!date.trim().equalsIgnoreCase("1")) {
            final int num = this.getMassage(type);
            CustomDateObject customDateObject = this.customDateService.createCustomDateObject(date);
            System.out.println("You write date: " + date + " " + type + ":" + num);
            customDateObject = this.customDateService.subtractHours(customDateObject, num);
            try {
                System.out.println(this.customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            } catch (final WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.subtractHour();
            }
        }
    }

    @RunTask (runTaskName = "Plus hour", order = 7)
    public void plusMinute() {
        final String date = this.getMassage();
        final String type = "minute";
        if (!date.trim().equalsIgnoreCase("1")) {
            final int num = this.getMassage(type);
            CustomDateObject customDateObject = this.customDateService.createCustomDateObject(date);
            System.out.println("You write date: " + date + " " + type + ": " + num);
            customDateObject = this.customDateService.plusMinutes(customDateObject, num);
            try {
                System.out.println(this.customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            } catch (final WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.plusMinute();
            }
        }
    }

    @RunTask (runTaskName = "Subtract minutes", order = 8)
    public void subtractMinute() {
        final String date = this.getMassage();
        final String type = "minute";
        if (!date.trim().equalsIgnoreCase("1")) {
            final int num = this.getMassage(type);
            CustomDateObject customDateObject = this.customDateService.createCustomDateObject(date);
            System.out.println("You write date: " + date + " " + type + ":" + num);
            customDateObject = this.customDateService.subtractMinutes(customDateObject, num);
            try {
                System.out.println(this.customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            } catch (final WrongFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Try again...");
                this.subtractMinute();
            }
        }
    }


    private String getMassage() {
        System.out.println("set input date or 1 to exit");
        System.out.println("date would be appropriate " + this.customDateFormatter.getInputFormat());
        return ConsoleReader.getStringFromConsole();
    }

    private int getMassage(final String type) {
        System.out.print("enter count " + type + ": ");
        return ConsoleReader.integerReader();
    }

}
