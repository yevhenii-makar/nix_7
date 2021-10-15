package com.yevheniimakar.entity;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long balance;

    @Column(nullable = false)
    String name;

    @NaturalId
    String accountsNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Access(AccessType.PROPERTY)
    User user;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)

    List<Operation> operations;

    public Account(){
        this.operations = new ArrayList<>();
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public void addOperation(Operation operation){
        this.operations.add(operation);
        this.balance = this.balance + operation.getValue();
        operation.setAccount(this);

    }
}
