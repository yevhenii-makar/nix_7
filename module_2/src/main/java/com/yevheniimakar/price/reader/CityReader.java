package com.yevheniimakar.price.reader;


import com.yevheniimakar.price.node.NodeInGraph;
import com.yevheniimakar.price.task.TaskInputData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CityReader {


    public TaskInputData getTaskInputData() {
        Map<Integer, NodeInGraph> nodes = new HashMap<>();
        TaskInputData taskInputData = null;

        String path = "cities.txt";
        int index = 1;
        int citiesCount = 0;
        String cityName;

        int neighborCount;
        int waysCount;
        List<String> starCity = new ArrayList<>();
        List<String> finishCity = new ArrayList<>();
        Map<Integer, Integer> indexCityAndPrise;
        try (FileReader fileReader = new FileReader(path); BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            citiesCount = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < citiesCount; i++) {
                cityName = bufferedReader.readLine();
                neighborCount = Integer.parseInt(bufferedReader.readLine());
                indexCityAndPrise = new HashMap<>();
                for (int j = 0; j < neighborCount; j++) {
                    String[] strings = bufferedReader.readLine().split(" ");
                    indexCityAndPrise.put(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
                }
                nodes.put(index, new NodeInGraph(cityName, indexCityAndPrise, index));
                index++;
            }
            waysCount = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < waysCount; i++) {
                String[] strings = bufferedReader.readLine().split(" ");
                starCity.add(strings[0]);
                finishCity.add(strings[1]);
            }
            taskInputData = new TaskInputData(citiesCount, nodes, waysCount, starCity, finishCity);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return taskInputData;
    }

}


