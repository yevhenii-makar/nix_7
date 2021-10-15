package com.yevheniimakar.dao;


import com.yevheniimakar.dto.AccountDto;
import com.yevheniimakar.dto.OperationDto;

import java.time.Instant;

public interface OperationDao {

    void addOperation(OperationDto operationDto);
}
