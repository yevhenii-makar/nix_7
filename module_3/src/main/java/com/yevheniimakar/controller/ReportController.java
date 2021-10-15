package com.yevheniimakar.controller;

import com.yevheniimakar.dto.AccountDto;
import com.yevheniimakar.dto.UserDto;
import com.yevheniimakar.service.ReportService;
import com.yevheniimakar.service.impl.ReportServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yevheniimakar.util.Util;

import java.time.Instant;
import java.util.List;

public class ReportController {

    private static final Logger log = LoggerFactory.getLogger(ReportController.class);

    ReportService reportService;


    public ReportController() {
        this.reportService = new ReportServiceImpl();
    }

    public void createReport(UserDto userDto) {

        List<AccountDto> accountDtoList = userDto.getAccountDtos();
        Long id = null;

        System.out.println("Accounts:");
        System.out.println("id | balance | name | accountsNumber");
        for (AccountDto a : accountDtoList) {
            System.out.println(a.getId() + " | " + a.getBalance() + " | " + a.getName() + " | " + a.getAccountsNumber());
        }

        try {
            id = Util.getLongFromConsole();

        } catch (NumberFormatException n) {
            log.error("Incorrect entered value of account id", n);
        }

        System.out.println("choice account id to operation:");
        System.out.println("Enter period to report");
        System.out.println("Enter start data in format 2018-07-17T09:59:51.312Z");
        Instant star = Instant.parse(Util.getStringFromConsole());
        System.out.println("Enter start data in format 2018-07-17T09:59:51.312Z");
        Instant end = Instant.parse(Util.getStringFromConsole());
        reportService.createCsvFileReport(id, star, end);
    }
}
