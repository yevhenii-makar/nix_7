package com.yevheniimakar.dao;

import com.yevheniimakar.entity.Account;

import java.util.List;

public interface AccountDao {


    List<Account> getAll();

    Account getAccountById(Long id);

    List<Account> getByUserId(Long id);
}
