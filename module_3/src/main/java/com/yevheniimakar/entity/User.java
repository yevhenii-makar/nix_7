package com.yevheniimakar.entity;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NaturalId
    @Column(nullable = false)
    String phoneNumber;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Account> accounts;

    public User(Long id, String phoneNumber, String firstName, String lastName, List<Account> accounts) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = accounts;
    }

    public User() {
        this.accounts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        this.accounts.add(account);
        account.setUser(this);
    }
}
