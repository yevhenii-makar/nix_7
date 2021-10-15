package com.yevheniimakar.service.impl;

import com.yevheniimakar.dao.LessonDao;
import com.yevheniimakar.dao.impl.LessonDaoImpl;
import com.yevheniimakar.dto.LessonDTO;
import com.yevheniimakar.entity.Lesson;
import com.yevheniimakar.service.LessonService;


public class LessonServiceImpl implements LessonService {


    LessonDao lessonDao;

    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public LessonDTO getNextLesson(long id) {

        return getLessonDtoFromLesson(lessonDao.getNextLessonByStudentIdHQL(id));

    }

    private LessonDTO getLessonDtoFromLesson(Lesson lesson) {

        return lesson != null
                ? new LessonDTO(
                lesson.getId(),
                lesson.getTheme().getName(),
                lesson.getDateTime(),
                lesson.getTeacher().getFirstName(),
                lesson.getTeacher().getLastName(),
                lesson.getGroup().getName()
        ) : null;

    }
}
