package com.yevheniimakar.dao.impl;

import com.yevheniimakar.config.DataSource;
import com.yevheniimakar.dao.AccountDao;
import com.yevheniimakar.entity.Account;
import com.yevheniimakar.exeption.ObjectNotFoundException;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class AccountDaoImpl implements AccountDao {

    SessionFactory sessionFactory;

    public AccountDaoImpl() {
        this.sessionFactory = DataSource.getSessionFactory();
    }


    @Override
    public List<Account> getAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        TypedQuery<Account> query = entityManager.createQuery("from Account", Account.class);
        List<Account> accountList = query.getResultList();

        if (accountList != null) {
            return accountList;
        } else {
            throw new ObjectNotFoundException("Account notFound");
        }
    }

    @Override
    public Account getAccountById(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        Account account = entityManager.find(Account.class, id);

        if (account != null) {
            entityManager.close();
            return account;
        } else {
            throw new ObjectNotFoundException("Account with id: " + id + " notFound");
        }
    }

    @Override
    public List<Account> getByUserId(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        TypedQuery<Account> query = entityManager.createQuery("from Account where users_id = " + id, Account.class);
        List<Account> accountList = query.getResultList();
        if (accountList != null) {
            return accountList;
        } else {
            throw new ObjectNotFoundException("Account by user id" + id + " notFound");
        }
    }
}
