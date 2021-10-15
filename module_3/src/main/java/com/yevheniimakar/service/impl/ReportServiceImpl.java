package com.yevheniimakar.service.impl;

import com.yevheniimakar.dao.ReportDao;
import com.yevheniimakar.dao.impl.ReportDaoImpl;
import com.yevheniimakar.entity.Report;
import com.yevheniimakar.service.ReportService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ReportServiceImpl implements ReportService {

    ReportDao reportDao = new ReportDaoImpl();

    @Override
    public void createCsvFileReport(Long accountId, Instant star, Instant end) {
        List<String> reportList = getListStringFromListReport(reportDao.getOperationReportForPeriodByAccount(accountId, star, end));

        try (FileWriter fileWriter = new FileWriter("report.csv"); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            for (String s: reportList) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String> getListStringFromListReport(List<Report> reportList){
        List<String> stringList = new ArrayList<>(reportList.size()+1);
        stringList.add("category,value,account,date");
        for (Report r: reportList) {
            stringList.add(r.getCategory()+","
                    +r.getValue()+","
                    +r.getAccount()+","
                    +r.getDate());
        }
        return stringList;

    }
}
