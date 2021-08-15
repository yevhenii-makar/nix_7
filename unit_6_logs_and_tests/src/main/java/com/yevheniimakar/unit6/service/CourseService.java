package com.yevheniimakar.unit6.service;

import com.yevheniimakar.unit6.service.objects.CourseObject;
import com.yevheniimakar.unit6.service.objects.StudentObject;

public interface CourseService {
    CourseObject getCourseByIdWithCourses(int id);

    CourseObject getCourseByIdWithoutCourses(int id);

    void createCourse(CourseObject course);

    void deleteCourseById(int id);

    void updateCourse(CourseObject course);

    CourseObject[] getCoursesListByStudentOrNull(StudentObject student);

    CourseObject[] getAllCourseObject();
}
