package com.yevheniimakar.dao.impl;

import com.yevheniimakar.dao.OccupationDao;
import com.yevheniimakar.entity.Group;
import com.yevheniimakar.entity.Occupation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;


public class OccupationDaoImpl implements OccupationDao {

    @Override
    public Occupation getNextOccupationByStudentId(EntityManager entityManager) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Occupation> cq = cb.createQuery(Occupation.class);
        Root<Occupation> occupation = cq.from(Occupation.class);

        CriteriaQuery<Occupation> wherePlayersEmpty = cq.select(occupation).where(cb.
                isEmpty(occupation.get("players")));

    }

}
