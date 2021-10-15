package com.yevheniimakar.service;

import com.yevheniimakar.dto.LessonDTO;
import com.yevheniimakar.entity.Lesson;


public interface LessonService {

    LessonDTO getNextLesson(long id);
}
