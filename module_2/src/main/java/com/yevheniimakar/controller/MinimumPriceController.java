package com.yevheniimakar.controller;

import com.yevhenii.makar.ConsoleReader;
import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;
import com.yevheniimakar.price.reader.CityReader;
import com.yevheniimakar.price.service.DijkstrasAlgorithm;
import com.yevheniimakar.price.service.impl.DijkstrasAlgorithmImpl;
import com.yevheniimakar.price.task.TaskInputData;


@Task (taskName = "Get minimum price", order = 3)

public class MinimumPriceController {

    CityReader cityReader = new CityReader();
    DijkstrasAlgorithm dijkstrasAlgorithm = new DijkstrasAlgorithmImpl();

    @RunTask (runTaskName = "minimum prise", order = 0)
    public void getMinimumPrise() {
        System.out.println("save file \"cities.txt\" to directory \"module_2\" and press any key");
        ConsoleReader.getStringFromConsole();
        TaskInputData taskInputData = cityReader.getTaskInputData();
        for (int i = 0; i < taskInputData.getStartCity().size(); i++) {
            System.out.println("from " + taskInputData.getStartCity().get(i) + " to" + taskInputData.getFinishCity().get(i) + " is =" + dijkstrasAlgorithm.getMinimumPathLength(taskInputData.getNodeInGraphMap(), taskInputData.getStartCity().get(i), taskInputData.getFinishCity().get(i)));
        }
    }

}
