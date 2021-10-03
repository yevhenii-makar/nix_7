package com.yevheniimakar;

import com.yevheniimakar.entity.Group;
import com.yevheniimakar.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;


public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            EntityManager entityManager = sessionFactory.createEntityManager();

            addStudent(entityManager);
        }
    }

    private static void addStudent(EntityManager entityManager) {

        try {
            entityManager.getTransaction().begin();
            Student student = new Student();
            student.setFirstName("Vasya");
            student.setLastName("Pupkin");
            student.seteMail("Pupkin@vasya.com");

            entityManager.persist(student);

            Group group = new Group();
            group.setName("group1");
            group.addStudent(student);

            entityManager.persist(group);

            entityManager.merge(student);


            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

}
