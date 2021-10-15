package com.yevheniimakar.dto;

import com.yevheniimakar.entity.User;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

public class AccountDto {

    Long id;


    Long balance;


    String name;


    String accountsNumber;


    Long userDtoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountsNumber() {
        return accountsNumber;
    }

    public void setAccountsNumber(String accountsNumber) {
        this.accountsNumber = accountsNumber;
    }

    public Long getUserDtoId() {
        return userDtoId;
    }

    public void setUserDtoId(Long userDtoId) {
        this.userDtoId = userDtoId;
    }
}
