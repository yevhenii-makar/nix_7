package com.yevheniimakar.date.service;

import com.yevheniimakar.date.object.CustomDateObject;

import java.util.List;


public interface CustomDateService {

    CustomDateObject createCustomDateObject(String dateObject);

    CustomDateObject createCustomDateObject(Long dateObject);

    CustomDateObject plusMilliseconds(CustomDateObject customDateObject, long milliseconds);

    CustomDateObject plusSeconds(CustomDateObject customDateObject, int seconds);

    CustomDateObject plusMinutes(CustomDateObject customDateObject, int minutes);

    CustomDateObject plusHours(CustomDateObject customDateObject, int hours);

    CustomDateObject plusDays(CustomDateObject customDateObject, int days);

    CustomDateObject plusMonths(CustomDateObject customDateObject, int months);

    CustomDateObject plusYears(CustomDateObject customDateObject, int years);

    CustomDateObject subtractMilliseconds(CustomDateObject customDateObject, Long milliseconds);

    CustomDateObject subtractSeconds(CustomDateObject customDateObject, int seconds);

    CustomDateObject subtractMinutes(CustomDateObject customDateObject, int minutes);

    CustomDateObject subtractHours(CustomDateObject customDateObject, int hours);

    CustomDateObject subtractDays(CustomDateObject customDateObject, int days);

    CustomDateObject subtractMonths(CustomDateObject customDateObject, int months);

    CustomDateObject subtractYears(CustomDateObject customDateObject, int years);

    String getDifference(CustomDateObject customDateObjectOne, CustomDateObject customDateObjectTwo);

    List<CustomDateObject> sort(List<CustomDateObject> customDateObjectList, boolean oldestFist);

}
