package com.yevheniimakar.dao.impl;

import com.yevheniimakar.dao.LessonDao;
import com.yevheniimakar.entity.Group;
import com.yevheniimakar.entity.Lesson;
import com.yevheniimakar.entity.Student;
import com.yevheniimakar.entity.Student_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;


public class LessonDaoImpl implements LessonDao {

    EntityManager entityManager;

    public LessonDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Lesson getNextLessonByStudentIdCriteria(long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery lesson = cb.createQuery();
        Root<Lesson> lessonRoot = lesson.from(Lesson.class);
        Root<Group> groupRoot = lesson.from(Group.class);
        Root<Student> studentRoot = lesson.from(Group.class);
        //        Fetch<Lesson,Group> lessonGroupFetch = lessonRoot.fetch(Lesson_.group);

        Predicate student = cb.equal(studentRoot.get(Student_.id), id);
        //Predicate group = cb.equal(Group_.);

        return null;
    }

    @Override
    public Lesson getNextLessonByStudentIdHQL(long id) {

        Lesson lesson = entityManager.createQuery(
                " SELECT o FROM Lesson o "
                        + "JOIN FETCH o.group g "
                        + "JOIN FETCH Student s ON (s.group  = g) "
                        + "WHERE s.id = :id AND o.dateTime >= :currentDateTime ORDER BY o.dateTime asc ", Lesson.class)
                .setMaxResults(1).setParameter("id", id)
                .setParameter("currentDateTime", LocalDateTime.now())
                .getResultStream().findAny().orElseThrow();

        return lesson;
    }

}
