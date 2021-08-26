package com.yevheniimakar.date.service.impl;

import com.yevheniimakar.date.constant.Constant;
import com.yevheniimakar.date.object.CustomDateObject;
import com.yevheniimakar.date.service.CustomDateFormatter;
import com.yevheniimakar.exceptions.WrongDataException;
import com.yevheniimakar.exceptions.WrongFormatException;
import org.apache.commons.text.CaseUtils;


public class CustomDateFormatterImpl implements CustomDateFormatter {

    private static String customDateFormatInput = "dd-MM-yyyy hh:mm:ss:SSS";
    private static String customDateFormatOutput = "dd-MM-yyyy hh:mm:ss:SSS";

    public String getInputFormat() {
        return customDateFormatInput;
    }

    @Override
    public void setInputFormat(final String format) {

        if (this.checkDateFormat(format)) {
            customDateFormatInput = format;
        } else {
            throw new WrongFormatException("format " + format + " is not valid");
        }
    }

    public String getOutputFormat() {
        return customDateFormatOutput;
    }

    @Override
    public void setOutputFormat(final String format) {
        if (this.checkDateFormat(format)) {
            customDateFormatOutput = format;
        } else {
            throw new WrongFormatException("format " + format + " is not valid");
        }
    }

    @Override
    public String convertDateFromCustomDateToString(final CustomDateObject customDateObject) {
        return this.convertDateFromMillisecondsToString(customDateObject.getDateInMilliseconds());
    }

    @Override
    public String convertDateFromMillisecondsToString(final Long millisecondsSum) {

        long millisecondsSumCountable = millisecondsSum;
        int years = 0;
        int months = 1;
        int days = 1;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        int milliseconds = 0;

        final int countYears = (int) (millisecondsSumCountable / Constant.MILLISECONDS_IN_ONE_YEAR);
        final int countLeapYearsFromMilliseconds = this.countLeapYearsBefore(millisecondsSumCountable);
        int countLeapYearsFromYearCount = this.countLeapYearsBefore(countYears);

        if (countLeapYearsFromMilliseconds == countLeapYearsFromYearCount && (((long) countYears * Constant.MILLISECONDS_IN_ONE_LEAP_YEAR) + (countLeapYearsFromYearCount * Constant.MILLISECONDS_IN_ONE_DAY)) < millisecondsSum) {
            years = countYears;
        } else {
            years = countYears - (countLeapYearsFromYearCount / 365);
            countLeapYearsFromYearCount = this.countLeapYearsBefore(years);
        }

        final long r1 = (long) years * Constant.MILLISECONDS_IN_ONE_LEAP_YEAR;

        millisecondsSumCountable = millisecondsSumCountable - ((((long) years) * Constant.MILLISECONDS_IN_ONE_YEAR) + (countLeapYearsFromYearCount * Constant.MILLISECONDS_IN_ONE_DAY));

        int alldays = 1;
        if (millisecondsSumCountable > Constant.MILLISECONDS_IN_ONE_DAY) {
            alldays = (int) ((millisecondsSumCountable) / Constant.MILLISECONDS_IN_ONE_DAY);
            millisecondsSumCountable -= ((alldays) * Constant.MILLISECONDS_IN_ONE_DAY);
            alldays++;
        }

        months = this.getMonthPerDays(alldays, this.isLeapYear(years));

        days = alldays - this.getDayFromMonthBeforeCurrentMonth(months, this.isLeapYear(years));

        if (millisecondsSumCountable >= Constant.MILLISECONDS_IN_ONE_HOUR) {
            hours = (int) (millisecondsSumCountable / Constant.MILLISECONDS_IN_ONE_HOUR);
            millisecondsSumCountable -= (hours * Constant.MILLISECONDS_IN_ONE_HOUR);
        }

        if (millisecondsSumCountable >= Constant.MILLISECONDS_IN_ONE_MINUTE) {
            minutes = (int) (millisecondsSumCountable / Constant.MILLISECONDS_IN_ONE_MINUTE);
            millisecondsSumCountable -= (minutes * Constant.MILLISECONDS_IN_ONE_MINUTE);
        }
        if (millisecondsSumCountable >= Constant.MILLISECONDS_IN_ONE_SECOND) {
            seconds = (int) (millisecondsSumCountable / Constant.MILLISECONDS_IN_ONE_SECOND);
            millisecondsSumCountable -= (seconds * Constant.MILLISECONDS_IN_ONE_SECOND);
        }
        milliseconds = (int) millisecondsSumCountable;

        return this.createDateString(years, months, days, hours, minutes, seconds, milliseconds);
    }

    private String createDateString(final int years, final int months, final int days, final int hours, final int minutes, final int seconds, final int milliseconds) {
        String date = customDateFormatOutput;
        String month;
        String year;
        final String day;
        final String hour;
        final String minute;
        final String second;
        final String millisecond;

        if (date.contains(Constant.PATTERN_YEAR_LONG)) {
            if (years == 0) {
                date = date.replaceAll(Constant.PATTERN_YEAR_LONG, "0000");
            } else {
                date = date.replaceAll(Constant.PATTERN_YEAR_LONG, "" + years);
            }
        }
        if (date.contains(Constant.PATTERN_YEAR_SHORT)) {
            year = "" + years;
            if (year.length() == 1 || year.length() == 2) {
                date = date.replaceAll(Constant.PATTERN_YEAR_SHORT, year);
            } else {
                year = "" + year.charAt(year.length() - 2) + year.charAt(year.length() - 1);
                date = date.replaceAll(Constant.PATTERN_YEAR_SHORT, year);
            }
        }
        if (date.contains(Constant.PATTERN_MONTH_NAME)) {
            month = CustomDateFormatterImpl.Month.values()[months - 1].name();
            month = CaseUtils.toCamelCase(month, true);
            date = date.replaceAll(Constant.PATTERN_MONTH_NAME, month);
        }
        if (date.contains(Constant.PATTERN_MONTH_LONG_NUMBER)) {
            month = (months > 9) ? "" + months : "0" + months;
            date = date.replaceAll(Constant.PATTERN_MONTH_LONG_NUMBER, month);
        }
        if (date.contains(Constant.PATTERN_MONTH_SHORT_NUMBER)) {
            date = date.replaceAll(Constant.PATTERN_MONTH_SHORT_NUMBER, "" + months);
        }
        if (date.contains(Constant.PATTERN_DAY_LONG)) {
            day = (days > 9) ? "" + days : "0" + days;
            date = date.replaceAll(Constant.PATTERN_DAY_LONG, day);
        }
        if (date.contains(Constant.PATTERN_DAY_SHORT)) {
            date = date.replaceAll(Constant.PATTERN_DAY_SHORT, "" + days);
        }
        if (date.contains(Constant.PATTERN_HOUR)) {
            hour = hours > 9 ? "" + hours : "0" + hours;
            date = date.replaceAll(Constant.PATTERN_HOUR, hour);
        }
        if (date.contains(Constant.PATTERN_MINUTE)) {
            minute = minutes > 9 ? "" + minutes : "0" + minutes;
            date = date.replaceAll(Constant.PATTERN_MINUTE, minute);
        }
        if (date.contains(Constant.PATTERN_SECOND)) {
            second = seconds > 9 ? "" + seconds : "0" + seconds;
            date = date.replaceAll(Constant.PATTERN_SECOND, second);
        }
        if (date.contains(Constant.PATTERN_MILLISECOND)) {
            millisecond = milliseconds > 99 ? "" + milliseconds : (milliseconds > 9 ? "0" + milliseconds : "00" + milliseconds);
            date = date.replaceAll(Constant.PATTERN_MILLISECOND, millisecond);
        }
        return date;
    }

    private int getMonthPerDays(final int days, final boolean leapYear) {
        boolean isContinue = true;
        int currentMonth = 1;
        int nextMonth = 2;
        while (isContinue) {
            if (days > this.getDayFromMonthBeforeCurrentMonth(currentMonth, leapYear) && days <= this.getDayFromMonthBeforeCurrentMonth(nextMonth, leapYear)) {
                isContinue = false;
                continue;
            }
            currentMonth++;
            nextMonth++;
        }
        return currentMonth;
    }

    public long convertDateFromStringToMilliseconds(final String date) throws WrongDataException {

        if (this.isDateNotMatchToFormat(date)) {
            throw new WrongDataException("Data " + date + "is not valid");
        } else {
            int years = 0;
            int leapYears = 0;
            int month = 1;
            int days = 1;
            int hour = 0;
            int minutes = 0;
            int seconds = 0;
            long milliseconds = 0;
            final String[] inputDateArray = date.split(Constant.PATTERN_ALLOWED_CHARACTERS, -1);
            final String[] inputDateFormatArray = customDateFormatInput.split(Constant.PATTERN_ALLOWED_CHARACTERS);

            for (int i = 0; i < inputDateFormatArray.length; i++) {
                if (inputDateFormatArray[i].equals(Constant.PATTERN_YEAR_LONG)) {
                    years = inputDateArray[i].isEmpty() ? 0 : Integer.parseInt(inputDateArray[i]);
                }
                if (inputDateFormatArray[i].equals(Constant.PATTERN_YEAR_SHORT)) {
                    years = inputDateArray[i].isEmpty() ? 0 : Integer.parseInt(inputDateArray[i]);
                }
                if (inputDateFormatArray[i].equals(Constant.PATTERN_MONTH_NAME)) {
                    month = inputDateArray[i].isEmpty() ? 1 : (Month.valueOf(inputDateArray[i].toUpperCase()).ordinal()) + 1;
                    this.isValidData(month, 12, "month");
                }
                if (inputDateFormatArray[i].equals(Constant.PATTERN_MONTH_LONG_NUMBER)) {
                    month = (inputDateArray[i].isEmpty() || Integer.parseInt(inputDateArray[i]) == 0) ? 1 : Integer.parseInt(inputDateArray[i]);
                    this.isValidData(month, 12, "month");
                }
                if (inputDateFormatArray[i].equals(Constant.PATTERN_MONTH_SHORT_NUMBER)) {
                    month = (inputDateArray[i].isEmpty() || Integer.parseInt(inputDateArray[i]) == 0) ? 1 : Integer.parseInt(inputDateArray[i]);
                    this.isValidData(month, 12, "month");
                }
                if (inputDateFormatArray[i].equals(Constant.PATTERN_DAY_LONG)) {
                    days = (inputDateArray[i].isEmpty() || Integer.parseInt(inputDateArray[i]) == 0) ? 1 : Integer.parseInt(inputDateArray[i]);
                    this.isValidData(days, this.getDayCurrentMonth(month, this.isLeapYear(years)), "day");
                }
                if (inputDateFormatArray[i].equals(Constant.PATTERN_DAY_SHORT)) {
                    days = (inputDateArray[i].isEmpty() || Integer.parseInt(inputDateArray[i]) == 0) ? 1 : Integer.parseInt(inputDateArray[i]);
                    this.isValidData(days, this.getDayCurrentMonth(month, this.isLeapYear(years)), "day");
                }
                if (inputDateFormatArray[i].equals(Constant.PATTERN_HOUR)) {
                    hour = inputDateArray[i].isEmpty() ? 0 : Integer.parseInt(inputDateArray[i]);
                    this.isValidData(hour, 23, "hour");
                }
                if (inputDateFormatArray[i].equals(Constant.PATTERN_MINUTE)) {
                    minutes = inputDateArray[i].isEmpty() ? 0 : Integer.parseInt(inputDateArray[i]);
                    this.isValidData(minutes, 59, "minutes");
                }
                if (inputDateFormatArray[i].equals(Constant.PATTERN_SECOND)) {
                    seconds = inputDateArray[i].isEmpty() ? 0 : Integer.parseInt(inputDateArray[i]);
                    this.isValidData(seconds, 59, "seconds");
                }
                if (inputDateFormatArray[i].equals(Constant.PATTERN_MILLISECOND)) {
                    milliseconds = inputDateArray[i].isEmpty() ? 0 : Integer.parseInt(inputDateArray[i]);
                    this.isValidData((int) milliseconds, 999, "milliseconds");
                }
            }

            leapYears = this.countLeapYearsBefore(years);

            milliseconds += (years * Constant.MILLISECONDS_IN_ONE_YEAR + leapYears * Constant.MILLISECONDS_IN_ONE_DAY);
            milliseconds += ((this.getDayFromMonthBeforeCurrentMonth(month, this.isLeapYear(years)) + (days - 1)) * Constant.MILLISECONDS_IN_ONE_DAY);
            milliseconds += (hour * Constant.MILLISECONDS_IN_ONE_HOUR);
            milliseconds += (minutes * Constant.MILLISECONDS_IN_ONE_MINUTE);

            return milliseconds;
        }
    }

    private void isValidData(final int data, final int compareWith, final String type) throws WrongDataException {
        if (data > compareWith) {
            throw new WrongDataException("wrong type: " + type);
        }
    }

    private boolean isDateNotMatchToFormat(final String date) {
        final String rex = customDateFormatInput.replaceAll(Constant.REG_EX_DATE_FORMAT, Constant.REG_EX_DATE_FORMAT_REPLACE).replaceAll(Constant.REG_EX_MONTH, Constant.REG_EX_MONTH_REPLACE);
        return !date.matches(rex);
    }

    private boolean checkDateFormat(final String dateFormat) {
        String checkedDateFormat = dateFormat;
        boolean isNotContainsYear = true;
        boolean isNotContainsMonth = true;
        boolean isNotContainsDay = true;

        if (this.isRepeatsSubstring(checkedDateFormat, Constant.PATTERN_YEAR_LONG)) {
            return false;
        } else {
            isNotContainsYear = checkedDateFormat.indexOf(Constant.PATTERN_YEAR_LONG) == -1;
            checkedDateFormat = checkedDateFormat.replaceAll(Constant.PATTERN_YEAR_LONG, "");
        }

        if ((!isNotContainsYear && checkedDateFormat.contains(Constant.PATTERN_YEAR_SHORT)) || this.isRepeatsSubstring(checkedDateFormat, Constant.PATTERN_YEAR_SHORT)) {
            return false;
        } else {
            checkedDateFormat = checkedDateFormat.replaceAll(Constant.PATTERN_YEAR_SHORT, "");
        }

        if (this.isRepeatsSubstring(checkedDateFormat, Constant.PATTERN_MONTH_NAME)) {
            return false;
        } else {
            isNotContainsMonth = checkedDateFormat.indexOf(Constant.PATTERN_MONTH_NAME) == -1;
            checkedDateFormat = checkedDateFormat.replaceAll(Constant.PATTERN_MONTH_NAME, "");
        }

        if ((!isNotContainsMonth && checkedDateFormat.contains(Constant.PATTERN_MONTH_LONG_NUMBER)) || this.isRepeatsSubstring(checkedDateFormat, Constant.PATTERN_MONTH_LONG_NUMBER)) {
            return false;
        } else {
            isNotContainsMonth = !isNotContainsMonth ? isNotContainsMonth : (checkedDateFormat.indexOf(Constant.PATTERN_MONTH_LONG_NUMBER) == -1);
            checkedDateFormat = checkedDateFormat.replaceAll(Constant.PATTERN_MONTH_LONG_NUMBER, "");
        }
        if ((!isNotContainsMonth && checkedDateFormat.contains(Constant.PATTERN_MONTH_SHORT_NUMBER)) || this.isRepeatsSubstring(checkedDateFormat, Constant.PATTERN_MONTH_SHORT_NUMBER)) {
            return false;
        } else {
            checkedDateFormat = checkedDateFormat.replaceAll(Constant.PATTERN_MONTH_SHORT_NUMBER, "");
        }

        if (this.isRepeatsSubstring(checkedDateFormat, Constant.PATTERN_DAY_LONG)) {
            return false;
        } else {
            isNotContainsDay = checkedDateFormat.indexOf(Constant.PATTERN_DAY_LONG) == -1;
            checkedDateFormat = checkedDateFormat.replaceAll(Constant.PATTERN_DAY_LONG, "");
        }
        if (this.isRepeatsSubstring(checkedDateFormat, Constant.PATTERN_DAY_SHORT)) {
            return false;
        } else {
            isNotContainsDay = !isNotContainsDay ? isNotContainsDay : (checkedDateFormat.indexOf(Constant.PATTERN_DAY_SHORT) == -1);
            checkedDateFormat = checkedDateFormat.replaceAll(Constant.PATTERN_DAY_SHORT, "");
        }
        if (this.isRepeatsSubstring(checkedDateFormat, Constant.PATTERN_HOUR)) {
            return false;
        } else {
            checkedDateFormat = checkedDateFormat.replaceAll(Constant.PATTERN_HOUR, "");
        }
        if (this.isRepeatsSubstring(checkedDateFormat, Constant.PATTERN_MINUTE)) {
            return false;
        } else {
            checkedDateFormat = checkedDateFormat.replaceAll(Constant.PATTERN_MINUTE, "");
        }
        if (this.isRepeatsSubstring(checkedDateFormat, Constant.PATTERN_SECOND)) {
            return false;
        } else {
            checkedDateFormat = checkedDateFormat.replaceAll(Constant.PATTERN_SECOND, "");
        }
        if (this.isRepeatsSubstring(checkedDateFormat, Constant.PATTERN_MILLISECOND)) {
            return false;
        } else {
            checkedDateFormat = checkedDateFormat.replaceAll(Constant.PATTERN_MILLISECOND, "");
        }

        return checkedDateFormat.replaceAll(Constant.PATTERN_ALLOWED_CHARACTERS, "").length() <= 0;
    }

    private boolean isRepeatsSubstring(final String string, final String substring) {
        return (string.indexOf(substring) != string.lastIndexOf(substring));
    }

    private boolean isLeapYear(final int year) {
        boolean leapYear = false;
        if (year % 4 == 0) {
            leapYear = true;
        }
        if (year % 100 == 0) {
            leapYear = false;
        }
        if (year % 400 == 0) {
            leapYear = true;
        }
        return leapYear;
    }

    private int countLeapYearsBefore(int year) {
        --year;
        if (year > 0) {
            return (year / 4) - (year / 100) + (year / 400);
        }
        return 0;
    }

    private int countLeapYearsBefore(final long milliseconds) {
        if (milliseconds > 0) {
            return (int) (((milliseconds / 4) - (milliseconds / 100) + (milliseconds / 400)) / Constant.MILLISECONDS_IN_ONE_LEAP_YEAR);
        }
        return 0;
    }

    private int getDayFromMonthBeforeCurrentMonth(int month, final boolean isLeapYear) {
        --month;
        int result = 0;
        switch (month) {
            case 12: {
                result += 31;
            }
            case 11: {
                result += 30;
            }
            case 10: {
                result += 31;
            }
            case 9: {
                result += 30;
            }
            case 8: {
                result += 31;
            }
            case 7: {
                result += 31;
            }
            case 6: {
                result += 30;
            }
            case 5: {
                result += 31;
            }
            case 4: {
                result += 30;
            }
            case 3: {
                result += 31;
            }
            case 2: {
                result += isLeapYear ? 29 : 28;
            }
            case 1: {
                result += 31;
            }
            default:
                result += 0;
        }
        return result;
    }

    private int getDayCurrentMonth(final int month, final boolean isLeapYear) {
        int result = 0;
        switch (month) {
            case 12:
            case 1:
            case 10:
            case 8:
            case 7:
            case 5:
            case 3: {
                result = 31;
            }
            break;
            case 11:
            case 9:
            case 6:
            case 4: {
                result = 30;
            }
            break;
            case 2: {
                result = isLeapYear ? 29 : 28;
            }
            break;
        }
        return result;
    }

    public enum Month {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
    }

}
