package com.yevheniimakar.dao;


import com.yevheniimakar.domain.Course;

import java.util.List;


public interface CourseDao{

    Course getCourseById(int id);

    void createCourse(Course course);

    void deleteCourseById(int id);

    void updateCourse(Course course);

    void updateCourse(Course course, List<Integer> studentListIds);

    List<Course> getCoursesListByStudentIdOrNull(int studentId);

    List<Course>  getAllCourses();

}
