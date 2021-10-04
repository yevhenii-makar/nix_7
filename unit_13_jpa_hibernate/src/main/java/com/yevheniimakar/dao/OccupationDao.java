package com.yevheniimakar.dao;

import com.yevheniimakar.entity.Occupation;

import javax.persistence.EntityManager;


public interface OccupationDao {

    Occupation getNextOccupationByStudentIdCriteria(EntityManager entityManager, long id);
    Occupation getNextOccupationByStudentIdHQL(EntityManager entityManager, long id);
}
