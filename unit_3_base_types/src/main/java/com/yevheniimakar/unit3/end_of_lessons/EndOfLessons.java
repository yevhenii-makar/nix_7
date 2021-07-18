package com.yevheniimakar.unit3.end_of_lessons;

public class EndOfLessons {

    public String endOfLessons(int i) {
        int starHH = 9;
        int starMM = 0;
        int lessonTime = 45;
        int breakTimeShort = 5;
        int breakTimeLong = 15;

        int timeAtSchool = (lessonTime + breakTimeShort) * i + ((breakTimeLong - breakTimeShort) * (i / 2)) - (breakTimeLong - ((breakTimeLong - breakTimeShort) * (i % 2)));
        int minutesPerDay = starHH * 60 + starMM + timeAtSchool;

        int endHH = (minutesPerDay / 60) % 24;
        int endMM = minutesPerDay % 60;

        return "Lessons end at: " + getHourAndMinutesFromIntToString(endHH, endMM);

    }

    private String getHourAndMinutesFromIntToString(int hour, int minutes) {
        String[] hourArr = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutesArr = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
                "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

        return hourArr[hour] + " : " + minutesArr[minutes];

    }
}
