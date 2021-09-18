package com.yevheniimakar.date.constant;

public class Constant {

    public static final long MILLISECONDS_IN_ONE_SECOND = 1000L;
    public static final long MILLISECONDS_IN_ONE_MINUTE = 60000L;
    public static final long MILLISECONDS_IN_ONE_HOUR = 3600000L;
    public static final long MILLISECONDS_IN_ONE_DAY = 86400000L;
    public static final long MILLISECONDS_IN_ONE_YEAR = 31536000000L;
    public static final long MILLISECONDS_IN_ONE_LEAP_YEAR = 31622400000L;

    public static final String PATTERN_YEAR_LONG = "yyyy";
    public static final String PATTERN_YEAR_SHORT = "yy";
    public static final String PATTERN_MONTH_SHORT_NUMBER = "M";
    public static final String PATTERN_MONTH_LONG_NUMBER = "MM";
    public static final String PATTERN_MONTH_NAME = "MMM";
    public static final String PATTERN_DAY_LONG = "dd";
    public static final String PATTERN_DAY_SHORT = "d";
    public static final String PATTERN_HOUR = "hh";
    public static final String PATTERN_MINUTE = "mm";
    public static final String PATTERN_SECOND = "ss";
    public static final String PATTERN_MILLISECOND = "SSS";

    public static final int DEFAULT_YEAR = 0;
    public static final int DEFAULT_DAY = 1;

    public static final String PATTERN_ALLOWED_CHARACTERS = "[/\\-.: ]";
    public static final String PATTERN_DISALLOWED_CHARACTERS = "[^/-.: ]";
    public static final String PATTERN_BLOCK_DATE = "^(d|M|y)[/-dMy]+(d|M|y)";
    public static final String REG_EX_BLOCK_TIME = "(h|m|s|S)[-: hmsS]+(h|m|s|S)$";

    public static final String REG_EX_DATE_FORMAT = "(d+|Y+|y+|h+|m+|s+|S+)";
    public static final String REG_EX_DATE_FORMAT_REPLACE = "[0-9]{0,4}";
    public static final String REG_EX_MONTH = "(M){1,}";
    public static final String REG_EX_MONTH_REPLACE = "[a-zA-Z0-9]{0,}";

}
