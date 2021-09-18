package com.yevheniimakar.controller;

import com.yevhenii.makar.ConsoleReader;
import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;
import com.yevheniimakar.date.object.DateObject;
import com.yevheniimakar.date.service.CustomDateFormatter;
import com.yevheniimakar.date.service.CustomDateService;
import com.yevheniimakar.date.service.impl.CustomDateFormatterImpl;
import com.yevheniimakar.date.service.impl.CustomDateServiceImpl;
import com.yevheniimakar.filedatereaderwriter.FileReaderWriter;

import java.util.ArrayList;
import java.util.List;


@Task (taskName = "Date converter", order = 1)
public class DateController {

    CustomDateFormatter customDateFormatter = new CustomDateFormatterImpl();
    CustomDateService customDateService = new CustomDateServiceImpl();

    @RunTask (runTaskName = "convert date from file", order = 0)
    public void convertDateFromFile() {
        System.out.println("save file \"dates.txt\" to directory \"module_2\" and press any key");
        ConsoleReader.getStringFromConsole();
        FileReaderWriter dateFileReaderWriter = new FileReaderWriter();
        List<String> listDates = dateFileReaderWriter.readDateFile();
        List<String> formattedListDate = new ArrayList<>();
        for (String date : listDates) {
            DateObject customDateObject = customDateService.createDateObject(date);
            if (customDateObject != null) {
                formattedListDate.add(customDateFormatter.convertDateFromCustomDateToString(customDateObject));
            }
        }
        dateFileReaderWriter.writeDateFile(formattedListDate);
    }

}
