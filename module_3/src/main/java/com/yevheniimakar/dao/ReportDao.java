package com.yevheniimakar.dao;

import com.yevheniimakar.entity.Report;

import java.time.Instant;
import java.util.List;

public interface ReportDao {

    //    public List<Report> getOperationReportForPeriodByAccount(AccountDto accountDto, Instant star, Instant end);
    List<Report> getOperationReportForPeriodByAccount(Long accountDto, Instant star, Instant end);
}