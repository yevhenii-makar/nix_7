package com.yevheniimakar.service.impl;

import com.yevheniimakar.dao.AccountDao;
import com.yevheniimakar.dao.impl.AccountDaoImpl;
import com.yevheniimakar.dto.AccountDto;
import com.yevheniimakar.entity.Account;
import com.yevheniimakar.service.AccountService;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    AccountDao accountDao;

    public AccountServiceImpl() {
        this.accountDao = new AccountDaoImpl();
    }

    @Override
    public List<AccountDto> getAccountDtoListFromAccountList(List<Account> accountList) {
        List<AccountDto> accountDtoList = new ArrayList<>(accountList.size());
        for (Account account: accountList) {
            accountDtoList.add(getAccountDtoFromAccount(account));
        }
        return accountDtoList;
    }

    @Override
    public List<AccountDto> getByUserID(Long id) {
        return getAccountDtoListFromAccountList(accountDao.getByUserId(id));
    }

    private AccountDto getAccountDtoFromAccount(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountsNumber(account.getAccountsNumber());
        accountDto.setBalance(account.getBalance());
        accountDto.setId(account.getId());
        accountDto.setName(account.getName());
        accountDto.setUserDtoId(account.getUser().getId());
        return accountDto;
    }
}
