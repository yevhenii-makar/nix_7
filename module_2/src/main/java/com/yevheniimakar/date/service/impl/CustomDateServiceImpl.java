package com.yevheniimakar.date.service.impl;

import com.yevheniimakar.date.constant.Constant;
import com.yevheniimakar.date.exceptions.WrongDataException;
import com.yevheniimakar.date.object.DateObject;
import com.yevheniimakar.date.service.CustomDateFormatter;
import com.yevheniimakar.date.service.CustomDateService;

import java.util.List;


public class CustomDateServiceImpl implements CustomDateService {

    CustomDateFormatter customDateFormatter = new CustomDateFormatterImpl();


    @Override
    public DateObject createDateObject(final String dateObject) throws WrongDataException {
        long milliseconds = this.customDateFormatter.convertDateFromStringToMilliseconds(dateObject);
        return milliseconds == 0 ? null : new DateObject(milliseconds);
    }

    @Override
    public DateObject createDateObject(final Long dateObject) {
        return new DateObject(dateObject);
    }


    @Override
    public DateObject plusMilliseconds(final DateObject DateObject, final long milliseconds) {
        return new DateObject(DateObject.getDateInMilliseconds() + milliseconds);
    }

    @Override
    public DateObject plusSeconds(final DateObject DateObject, final int seconds) {
        return this.plusMilliseconds(DateObject, seconds * Constant.MILLISECONDS_IN_ONE_SECOND);
    }

    @Override
    public DateObject plusMinutes(final DateObject DateObject, final int minutes) {
        return this.plusMilliseconds(DateObject, minutes * Constant.MILLISECONDS_IN_ONE_MINUTE);
    }

    @Override
    public DateObject plusHours(final DateObject DateObject, final int hours) {
        return this.plusMilliseconds(DateObject, hours * Constant.MILLISECONDS_IN_ONE_HOUR);
    }

    @Override
    public DateObject plusDays(final DateObject DateObject, final int days) {
        return this.plusMilliseconds(DateObject, days * Constant.MILLISECONDS_IN_ONE_DAY);
    }

    @Override
    public DateObject plusMonths(final DateObject DateObject, final int months) {
        return this.plusMilliseconds(DateObject, months * Constant.MILLISECONDS_IN_ONE_DAY * 30);
    }

    @Override
    public DateObject plusYears(final DateObject DateObject, final int years) {
        return this.plusMilliseconds(DateObject, years * Constant.MILLISECONDS_IN_ONE_YEAR);
    }

    @Override
    public DateObject subtractMilliseconds(final DateObject DateObject, final Long milliseconds) {
        return new DateObject(DateObject.getDateInMilliseconds() - milliseconds);
    }

    @Override
    public DateObject subtractSeconds(final DateObject DateObject, final int seconds) {
        return this.subtractMilliseconds(DateObject, seconds * Constant.MILLISECONDS_IN_ONE_SECOND);
    }

    @Override
    public DateObject subtractMinutes(final DateObject DateObject, final int minutes) {
        return this.subtractMilliseconds(DateObject, minutes * Constant.MILLISECONDS_IN_ONE_MINUTE);
    }

    @Override
    public DateObject subtractHours(final DateObject DateObject, final int hours) {
        return this.subtractMilliseconds(DateObject, hours * Constant.MILLISECONDS_IN_ONE_HOUR);
    }

    @Override
    public DateObject subtractDays(final DateObject DateObject, final int days) {
        return this.subtractMilliseconds(DateObject, days * Constant.MILLISECONDS_IN_ONE_DAY);
    }

    @Override
    public DateObject subtractMonths(final DateObject DateObject, final int months) {
        return this.subtractMilliseconds(DateObject, months * Constant.MILLISECONDS_IN_ONE_DAY * 30);
    }

    @Override
    public DateObject subtractYears(final DateObject DateObject, final int years) {
        return this.subtractMilliseconds(DateObject, years * Constant.MILLISECONDS_IN_ONE_YEAR);
    }


    @Override
    public String getDifference(final DateObject DateObjectOne, final DateObject DateObjectTwo) {
        return null;
    }

    @Override
    public List<DateObject> sort(final List<DateObject> DateObjectList, final boolean oldestFist) {
        return null;
    }

    //    public CustomDate(String date) {
    //        if (isDateMatchToFormat(date)) {
    //            this.dateInMilliseconds = convertDateFromStringToMilliseconds(date);
    //        }
    //        {
    //            //exception
    //        }
    //    }
}
