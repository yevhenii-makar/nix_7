package com.yevheniimakar;

import com.yevheniimakar.dao.OccupationDao;
import com.yevheniimakar.dao.impl.OccupationDaoImpl;
import com.yevheniimakar.entity.Group;
import com.yevheniimakar.entity.Occupation;
import com.yevheniimakar.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;


public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        OccupationDao occupationDao = new OccupationDaoImpl();


        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            EntityManager entityManager = sessionFactory.createEntityManager();

            Occupation occupation = occupationDao.getNextOccupationByStudentIdHQL(entityManager,1l);
        }
    }





}
