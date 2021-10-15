package com.yevheniimakar;

import com.yevheniimakar.dao.LessonDao;
import com.yevheniimakar.dao.impl.LessonDaoImpl;
import com.yevheniimakar.dto.LessonDTO;
import com.yevheniimakar.service.LessonService;
import com.yevheniimakar.service.impl.LessonServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.Date;


public class Main {

    public static void main(String[] args) {




        Configuration configuration = new Configuration().configure();
        LessonDao lessonDao ;
        LessonService lessonService ;

        EntityManager entityManager;
        LessonDTO lesson;
        long id = 1;

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            entityManager = sessionFactory.createEntityManager();
            lessonDao = new LessonDaoImpl(entityManager);
            lessonService = new LessonServiceImpl(lessonDao);
            lesson = lessonService.getNextLesson(id);
        }

        System.out.println(lesson);
    }


}
