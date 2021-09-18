package com.yevheniimakar.controller;

import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;
import com.yevheniimakar.filedatereaderwriter.FileReaderWriter;
import com.yevheniimakar.uniquename.UniqueName;

import java.util.List;


@Task (taskName = "unique name", order = 2)
public class NameController {

    UniqueName uniqueName = new UniqueName();
    FileReaderWriter fileReaderWriter = new FileReaderWriter();

    @RunTask (runTaskName = " get unique name using loop", order = 1)
    public void UniqueNameNameUsingLoop() {
        System.out.println("save file \"name.txt\" to directory \"module_2\" and press any key");
        List<String> names = fileReaderWriter.readNameFile();
        System.out.println();
        System.out.println("unique name - " + uniqueName.getUniqueNameUsingLoop(names));
    }

    @RunTask (runTaskName = " get unique name using stream", order = 2)
    public void UniqueNameNameUsingStream() {
        System.out.println("save file \"name.txt\" to directory \"module_2\" and press any key");
        List<String> names = fileReaderWriter.readNameFile();
        System.out.println();
        System.out.println("unique name - " + uniqueName.getUniqueNameUsingStream(names));
    }

}
