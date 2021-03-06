package com.yevheniimakar.unit6.logs.dao;

import com.yevheniimakar.unit6.logs.domain.Course;

public interface CourseDao {

    Course getCourseById(int id);

    void createCourse(Course course);

    void deleteCourseById(int id);

    void updateCourse(Course course);

    void updateCourse(Course course, int[] studentListIds);

    Course[] getCoursesListByStudentIdOrNull(int studentId);

    Course[] getAllCourses();
}
