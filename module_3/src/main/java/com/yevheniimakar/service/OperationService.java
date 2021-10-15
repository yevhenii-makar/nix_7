package com.yevheniimakar.service;

import com.yevheniimakar.dto.AccountDto;
import com.yevheniimakar.dto.CategoryDto;
import com.yevheniimakar.dto.UserDto;

public interface OperationService {

    void addOperation(AccountDto accountDto, CategoryDto categoryDto, Long value);


}
