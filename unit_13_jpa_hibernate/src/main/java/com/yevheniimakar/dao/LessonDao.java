package com.yevheniimakar.dao;

import com.yevheniimakar.entity.Lesson;

import javax.persistence.EntityManager;


public interface LessonDao {

    Lesson getNextLessonByStudentIdCriteria(long id);

    Lesson getNextLessonByStudentIdHQL(long id);
}
