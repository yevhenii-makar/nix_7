package com.yevheniimakar.unit6.dao.impl;

import com.yevheniimakar.unit6.dao.CourseDao;
import com.yevheniimakar.unit6.db.CourseDB;
import com.yevheniimakar.unit6.db.StudentCourseDB;
import com.yevheniimakar.unit6.domain.Course;

public class CourseDAOImpl implements CourseDao {

    private CourseDB courseDB = CourseDB.getInstance();
    private StudentCourseDB studentCourseDB = StudentCourseDB.getInstance();

    @Override
    public Course getCourseById(int id) {
        return courseDB.getCourseById(id);
    }

    @Override
    public void createCourse(Course course) {
        courseDB.createCourse(course);
    }

    @Override
    public void deleteCourseById(int id) {
        courseDB.deleteCourseById(id);
        studentCourseDB.updateListStudentsByCoursetId(id, new int[0]);
    }

    @Override
    public void updateCourse(Course course) {
        courseDB.updateCourse(course);
    }

    @Override
    public void updateCourse(Course course, int[] studentListIds) {
        courseDB.updateCourse(course);
        studentCourseDB.updateListStudentsByCoursetId(course.getId(), studentListIds);
    }

    @Override
    public Course[] getCoursesListByStudentIdOrNull(int studentId) {
        int[] courseIdDsList = studentCourseDB.getCourseIDsByStudentIDorNull(studentId);
        Course[] coursesList = null;
        if (courseIdDsList != null && courseIdDsList.length > 0) {
            coursesList = new Course[courseIdDsList.length];
            for (int i = 0; i < courseIdDsList.length; i++) {
                coursesList[i] = courseDB.getCourseById(courseIdDsList[i]);
            }
        }
        return coursesList;
    }

    @Override
    public Course[] getAllCourses() {
        return courseDB.getAllCourse();
    }
}
