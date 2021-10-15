package com.yevheniimakar.controller;


import com.yevheniimakar.dto.AccountDto;
import com.yevheniimakar.dto.CategoryDto;
import com.yevheniimakar.dto.UserDto;
import com.yevheniimakar.service.AccountService;
import com.yevheniimakar.service.CategoryService;
import com.yevheniimakar.service.OperationService;
import com.yevheniimakar.service.impl.AccountServiceImpl;
import com.yevheniimakar.service.impl.CategoryServiceImpl;
import com.yevheniimakar.service.impl.OperationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yevheniimakar.util.Util;

import java.util.List;

public class OperationController {

    private static final Logger log = LoggerFactory.getLogger(OperationController.class);

    OperationService operationService;
    AccountService accountService;
    CategoryService categoryService;


    public OperationController() {
        this.operationService = new OperationServiceImpl();
        this.accountService = new AccountServiceImpl();
        this.categoryService = new CategoryServiceImpl();

    }

    public void addOperation(UserDto userDto) {
        List<CategoryDto> categoryDtoList = categoryService.getAll();
        List<AccountDto> accountDtoList = userDto.getAccountDtos();
        AccountDto accountDto = null;
        CategoryDto categoryDto = null;
        Long accountDtoId;
        Long categoryDtoId;
        Long value = null;


        System.out.println("Accounts:");
        System.out.println("id | balance | name | accountsNumber");
        for (AccountDto a : accountDtoList) {
            System.out.println(a.getId() + " | " + a.getBalance() + " | " + a.getName() + " | " + a.getAccountsNumber());
        }
        System.out.println("choice account id to operation:");

        try {
            accountDtoId = Util.getLongFromConsole();
            accountDto = accountDtoList.stream().filter(ac -> ac.getId() == accountDtoId).findFirst().get();
        } catch (NumberFormatException n) {
            log.error("Incorrect entered value of account id", n);
        }

        System.out.println("Category");
        System.out.println("id | name ");
        for (CategoryDto c : categoryDtoList) {
            System.out.println(c.getId() + " | " + c.getName());
        }
        System.out.println("choice category id to operation:");

        try {
            categoryDtoId = Util.getLongFromConsole();
            categoryDto = categoryDtoList.stream().filter(ca -> ca.getId() == categoryDtoId).findFirst().get();
        } catch (NumberFormatException n) {
            log.error("Incorrect entered value of category id", n);
        }


        System.out.println("Enter value");
        try {
            value = Util.getLongFromConsole();
        } catch (NumberFormatException n) {
            log.error("Incorrect entered value", n);
        }

        operationService.addOperation(accountDto, categoryDto, value);
    }
}
