package com.yevheniimakar.dao.impl;

import com.yevheniimakar.config.DataSource;
import com.yevheniimakar.dao.AccountDao;
import com.yevheniimakar.dao.CategoryDao;
import com.yevheniimakar.dao.OperationDao;
import com.yevheniimakar.dto.OperationDto;
import com.yevheniimakar.entity.Account;
import com.yevheniimakar.entity.Category;
import com.yevheniimakar.entity.Operation;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
public class OperationDaoImpl implements OperationDao {

    private static final Logger log = LoggerFactory.getLogger(OperationDaoImpl.class);

    CategoryDao categoryDao;
    AccountDao accountDao;
    SessionFactory sessionFactory;

    public OperationDaoImpl() {

        categoryDao = new CategoryDaoImpl();
        accountDao = new AccountDaoImpl();
        sessionFactory = DataSource.getSessionFactory();

    }

    @Override
    public void addOperation(OperationDto operationDto) {

        EntityManager entityManager = sessionFactory.createEntityManager();

        Category category = categoryDao.getCategoryById(operationDto.getCategoryDtoId());

        log.info("find category with id: {}", operationDto.getCategoryDtoId());

        Account account = accountDao.getAccountById(operationDto.getAccountDtoId());

        log.info("find account with id: {}", operationDto.getAccountDtoId());
        Operation operation = new Operation();
        operation.setValue(operationDto.getValue());
        operation.setDate(operationDto.getDate());
        category.addOperation(operation);
        account.addOperation(operation);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(operation);
            entityManager.merge(category);
            entityManager.merge(account);
            entityManager.merge(operation);
            entityManager.getTransaction().commit();
            log.info("Adding neq operation complete");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error("Transaction not complete ", e);
        }
        entityManager.close();

    }



}
