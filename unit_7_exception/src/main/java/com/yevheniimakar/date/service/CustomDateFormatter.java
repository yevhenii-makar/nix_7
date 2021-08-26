package com.yevheniimakar.date.service;

import com.yevheniimakar.date.object.CustomDateObject;


public interface CustomDateFormatter {

    void setInputFormat(String format);

    void setOutputFormat(String format);

    String convertDateFromMillisecondsToString(Long millisecondsSum);

    long convertDateFromStringToMilliseconds(String date);

    String getInputFormat();

    String getOutputFormat();

    String convertDateFromCustomDateToString(CustomDateObject customDateObject);

}
