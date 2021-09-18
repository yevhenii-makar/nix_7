package com.yevheniimakar.filedatereaderwriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileReaderWriter {

    public List<String> readDateFile() {
        List<String> dateList = new ArrayList<>();
        String path = "date.txt";
        String line;

        try (FileReader fileReader = new FileReader(path); BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while ((line = bufferedReader.readLine()) != null) {
                dateList.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dateList;
    }

    public List<String> readNameFile() {
        List<String> nameList = new ArrayList<>();
        String path = "name.txt";
        String line;

        try (FileReader fileReader = new FileReader(path); BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while ((line = bufferedReader.readLine()) != null) {
                nameList.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameList;
    }

    public void writeDateFile(List<String> dates) {
        String path = "converted_date.txt";
        try (FileWriter fileWriter = new FileWriter(path); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (String date : dates) {
                bufferedWriter.write(date);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
