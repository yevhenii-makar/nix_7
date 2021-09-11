package com.yevheniimakar.unit6.logs.service;

import com.yevheniimakar.unit6.logs.service.objects.CourseObject;
import com.yevheniimakar.unit6.logs.service.objects.StudentObject;

public interface CourseService {
    CourseObject getCourseByIdWithCourses(int id);

    CourseObject getCourseByIdWithoutCourses(int id);

    void createCourse(CourseObject course);

    void deleteCourseById(int id);

    void updateCourse(CourseObject course);

    CourseObject[] getCoursesListByStudentOrNull(StudentObject student);

    CourseObject[] getAllCourseObject();
}
