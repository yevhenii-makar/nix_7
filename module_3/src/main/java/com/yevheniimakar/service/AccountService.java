package com.yevheniimakar.service;

import com.yevheniimakar.dto.AccountDto;
import com.yevheniimakar.entity.Account;

import java.util.List;

public interface AccountService {

    List<AccountDto> getAccountDtoListFromAccountList(List<Account> accountList);

    List<AccountDto> getByUserID(Long id);

}
