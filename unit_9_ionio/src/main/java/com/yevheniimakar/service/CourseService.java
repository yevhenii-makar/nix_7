package com.yevheniimakar.service;


import com.yevheniimakar.service.objects.CourseObject;
import com.yevheniimakar.service.objects.StudentObject;

import java.util.List;


public interface CourseService {
    CourseObject getCourseByIdWithStudents(int id);

    CourseObject getCourseByIdWithoutStudents(int id);

    void createCourse(CourseObject course);

    void deleteCourseById(int id);

    void updateCourse(CourseObject course);

    List<CourseObject> getCoursesListByStudentOrNull(StudentObject student);

    List<CourseObject> getAllCourseObject();
}
