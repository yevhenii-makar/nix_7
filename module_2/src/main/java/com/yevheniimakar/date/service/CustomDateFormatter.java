package com.yevheniimakar.date.service;

import com.yevheniimakar.date.object.DateObject;


public interface CustomDateFormatter {


    String convertDateFromMillisecondsToString(Long millisecondsSum);

    long convertDateFromStringToMilliseconds(String date);


    String convertDateFromCustomDateToString(DateObject dateObject);

}
