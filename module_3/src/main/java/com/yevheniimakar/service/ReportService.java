package com.yevheniimakar.service;

import java.time.Instant;

public interface ReportService {

    void createCsvFileReport(Long accountId, Instant star, Instant end);
}
