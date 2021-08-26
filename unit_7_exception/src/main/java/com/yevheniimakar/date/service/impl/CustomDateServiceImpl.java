package com.yevheniimakar.date.service.impl;

import com.yevheniimakar.date.constant.Constant;
import com.yevheniimakar.date.object.CustomDateObject;
import com.yevheniimakar.date.service.CustomDateFormatter;
import com.yevheniimakar.date.service.CustomDateService;
import com.yevheniimakar.exceptions.WrongDataException;

import java.util.List;


public class CustomDateServiceImpl implements CustomDateService {

    CustomDateFormatter customDateFormatter = new CustomDateFormatterImpl();


    @Override
    public CustomDateObject createCustomDateObject(final String dateObject) throws WrongDataException {
        return new CustomDateObject(this.customDateFormatter.convertDateFromStringToMilliseconds(dateObject));
    }

    @Override
    public CustomDateObject createCustomDateObject(final Long dateObject) {
        return new CustomDateObject(dateObject);
    }


    @Override
    public CustomDateObject plusMilliseconds(final CustomDateObject customDateObject, final long milliseconds) {
        return new CustomDateObject(customDateObject.getDateInMilliseconds() + milliseconds);
    }

    @Override
    public CustomDateObject plusSeconds(final CustomDateObject customDateObject, final int seconds) {
        return this.plusMilliseconds(customDateObject, seconds * Constant.MILLISECONDS_IN_ONE_SECOND);
    }

    @Override
    public CustomDateObject plusMinutes(final CustomDateObject customDateObject, final int minutes) {
        return this.plusMilliseconds(customDateObject, minutes * Constant.MILLISECONDS_IN_ONE_MINUTE);
    }

    @Override
    public CustomDateObject plusHours(final CustomDateObject customDateObject, final int hours) {
        return this.plusMilliseconds(customDateObject, hours * Constant.MILLISECONDS_IN_ONE_HOUR);
    }

    @Override
    public CustomDateObject plusDays(final CustomDateObject customDateObject, final int days) {
        return this.plusMilliseconds(customDateObject, days * Constant.MILLISECONDS_IN_ONE_DAY);
    }

    @Override
    public CustomDateObject plusMonths(final CustomDateObject customDateObject, final int months) {
        return this.plusMilliseconds(customDateObject, months * Constant.MILLISECONDS_IN_ONE_DAY * 30);
    }

    @Override
    public CustomDateObject plusYears(final CustomDateObject customDateObject, final int years) {
        return this.plusMilliseconds(customDateObject, years * Constant.MILLISECONDS_IN_ONE_YEAR);
    }

    @Override
    public CustomDateObject subtractMilliseconds(final CustomDateObject customDateObject, final Long milliseconds) {
        return new CustomDateObject(customDateObject.getDateInMilliseconds() - milliseconds);
    }

    @Override
    public CustomDateObject subtractSeconds(final CustomDateObject customDateObject, final int seconds) {
        return this.subtractMilliseconds(customDateObject, seconds * Constant.MILLISECONDS_IN_ONE_SECOND);
    }

    @Override
    public CustomDateObject subtractMinutes(final CustomDateObject customDateObject, final int minutes) {
        return this.subtractMilliseconds(customDateObject, minutes * Constant.MILLISECONDS_IN_ONE_MINUTE);
    }

    @Override
    public CustomDateObject subtractHours(final CustomDateObject customDateObject, final int hours) {
        return this.subtractMilliseconds(customDateObject, hours * Constant.MILLISECONDS_IN_ONE_HOUR);
    }

    @Override
    public CustomDateObject subtractDays(final CustomDateObject customDateObject, final int days) {
        return this.subtractMilliseconds(customDateObject, days * Constant.MILLISECONDS_IN_ONE_DAY);
    }

    @Override
    public CustomDateObject subtractMonths(final CustomDateObject customDateObject, final int months) {
        return this.subtractMilliseconds(customDateObject, months * Constant.MILLISECONDS_IN_ONE_DAY * 30);
    }

    @Override
    public CustomDateObject subtractYears(final CustomDateObject customDateObject, final int years) {
        return this.subtractMilliseconds(customDateObject, years * Constant.MILLISECONDS_IN_ONE_YEAR);
    }


    @Override
    public String getDifference(final CustomDateObject customDateObjectOne, final CustomDateObject customDateObjectTwo) {
        return null;
    }

    @Override
    public List<CustomDateObject> sort(final List<CustomDateObject> customDateObjectList, final boolean oldestFist) {
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
