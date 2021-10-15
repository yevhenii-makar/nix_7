package com.yevheniimakar.service.impl;

import com.yevheniimakar.dao.OperationDao;
import com.yevheniimakar.dao.impl.OperationDaoImpl;
import com.yevheniimakar.dto.*;
import com.yevheniimakar.service.AccountService;
import com.yevheniimakar.service.OperationService;

import java.time.Instant;

public class OperationServiceImpl implements OperationService {
    OperationDao operationDao;
    AccountService accountService;


    public OperationServiceImpl() {
        this.operationDao = new OperationDaoImpl();
        accountService = new AccountServiceImpl();
    }

    @Override
    public void addOperation(AccountDto accountDto, CategoryDto categoryDto, Long value) {

        OperationDto operationDto;
        if(categoryDto instanceof IncomeCategoryDto && value > 0L || categoryDto instanceof ConsumptionCategoryDto && value < 0L) {

            operationDto = new OperationDto();
            operationDto.setValue(value);
            operationDto.setAccountDtoId(accountDto.getId());
            operationDto.setCategoryDtoId(categoryDto.getId());
            operationDto.setDate(Instant.now());
            operationDao.addOperation(operationDto);
        }

    }


}
