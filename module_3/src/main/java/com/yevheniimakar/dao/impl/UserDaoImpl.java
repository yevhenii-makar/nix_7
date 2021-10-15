package com.yevheniimakar.dao.impl;

import com.yevheniimakar.config.DataSource;
import com.yevheniimakar.dao.AccountDao;
import com.yevheniimakar.dao.CategoryDao;
import com.yevheniimakar.dao.UserDao;
import com.yevheniimakar.entity.User;
import com.yevheniimakar.exeption.ObjectNotFoundException;
import javassist.NotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Transactional
public class UserDaoImpl implements UserDao {
    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);


    SessionFactory sessionFactory;

    public UserDaoImpl() {
        this.sessionFactory = DataSource.getSessionFactory();
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        EntityManager entityManager = sessionFactory.createEntityManager();


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery <User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        CriteriaQuery<User> userByPhoneNumber = criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("phoneNumber"),phoneNumber));

        TypedQuery<User> userTypedQuery = entityManager.createQuery(userByPhoneNumber);


        try {
            User u = userTypedQuery.getSingleResult();

            entityManager.close();
            log.info("Found user by phone number: {}", phoneNumber);
            return u;
        } catch (NoResultException nre){

            log.error("massage", nre);

        }
        throw new ObjectNotFoundException("User with phone number: " + phoneNumber +" not Found");

    }
}
