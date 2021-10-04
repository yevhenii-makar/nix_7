package com.yevheniimakar.dao.impl;

import com.yevheniimakar.dao.OccupationDao;
import com.yevheniimakar.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;


public class OccupationDaoImpl implements OccupationDao {

    @Override
    public Occupation getNextOccupationByStudentIdCriteria(EntityManager entityManager, long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery occupation = cb.createQuery();
        Root<Occupation> occupationRoot = occupation.from(Occupation.class);
        Root<Group> groupRoot = occupation.from(Group.class);
        Root<Student> studentRoot = occupation.from(Group.class);
//        Fetch<Occupation,Group> occupationGroupFetch = occupationRoot.fetch(Occupation_.group);

        Predicate student = cb.equal(studentRoot.get(Student_.id), id);
        //Predicate group = cb.equal(Group_.);




        return null;

    }

    @Override
    public Occupation getNextOccupationByStudentIdHQL(EntityManager entityManager, long id) {


        Occupation occupation = (Occupation) entityManager.createQuery(
                " select o from Occupation o " +
                        "join Group g ON o.group = g " +
                        "join Student s ON (s.group  = g and s.id = :id) " +
                        "WHERE o.dateTime >= :currentDateTime " +
                        "order by o.dateTime asc ", Occupation.class)
                .setMaxResults(1)
                .setParameter("id", id)
                .setParameter("currentDateTime", LocalDateTime.now())
                .getSingleResult();



        return occupation;
    }

}
