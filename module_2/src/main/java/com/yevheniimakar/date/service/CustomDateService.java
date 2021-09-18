package com.yevheniimakar.date.service;

import com.yevheniimakar.date.object.DateObject;

import java.util.List;


public interface CustomDateService {

    DateObject createDateObject(String dateObject);

    DateObject createDateObject(Long dateObject);

    DateObject plusMilliseconds(DateObject DateObject, long milliseconds);

    DateObject plusSeconds(DateObject DateObject, int seconds);

    DateObject plusMinutes(DateObject DateObject, int minutes);

    DateObject plusHours(DateObject DateObject, int hours);

    DateObject plusDays(DateObject DateObject, int days);

    DateObject plusMonths(DateObject DateObject, int months);

    DateObject plusYears(DateObject DateObject, int years);

    DateObject subtractMilliseconds(DateObject DateObject, Long milliseconds);

    DateObject subtractSeconds(DateObject DateObject, int seconds);

    DateObject subtractMinutes(DateObject DateObject, int minutes);

    DateObject subtractHours(DateObject DateObject, int hours);

    DateObject subtractDays(DateObject DateObject, int days);

    DateObject subtractMonths(DateObject DateObject, int months);

    DateObject subtractYears(DateObject DateObject, int years);

    String getDifference(DateObject DateObjectOne, DateObject DateObjectTwo);

    List<DateObject> sort(List<DateObject> DateObjectList, boolean oldestFist);

}
