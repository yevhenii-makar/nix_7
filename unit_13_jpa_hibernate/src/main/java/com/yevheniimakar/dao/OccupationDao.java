package com.yevheniimakar.dao;

import com.yevheniimakar.entity.Occupation;

import javax.persistence.EntityManager;


public interface OccupationDao {

    Occupation getNextOccupationByStudentId(EntityManager entityManager);
}
