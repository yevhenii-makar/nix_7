package com.yevheniimakar.dao.impl;


import com.yevheniimakar.config.DbCsvConfig;
import com.yevheniimakar.dao.CourseDao;
import com.yevheniimakar.domain.Course;
import com.yevheniimakar.domain.CourseStudent;

import java.util.ArrayList;
import java.util.List;


public class CourseDAOImpl implements CourseDao {

    DbCsvConfig<Course> db = new DbCsvConfig();
    DbCsvConfig<CourseStudent> dbT = new DbCsvConfig();

    @Override
    public Course getCourseById(int id) {
        return db.getByID(id, Course.class);
    }

    @Override
    public void createCourse(Course course) {
        db.add(course);
    }

    @Override
    public void deleteCourseById(int id) {
        db.delete(db.getByID(id, Course.class));
    }

    @Override
    public void updateCourse(Course course) {
        db.update(course);
    }

    @Override
    public void updateCourse(Course course, List<Integer> studentListIds) {
        db.update(course);
        List<CourseStudent> courseId = dbT.getByField("" + course.getId(), "courseId", CourseStudent.class);
        for (CourseStudent course_student : courseId) {
            dbT.delete(course_student);
        }
        for (int i : studentListIds) {
            dbT.add(new CourseStudent(i, course.getId()));
        }
    }

    @Override
    public List<Course> getCoursesListByStudentIdOrNull(int studentId) {
        List<CourseStudent> courseStudents = dbT.getByField(""+studentId, "studentId", CourseStudent.class);
        List<Course> courses = new ArrayList<>();
        for (CourseStudent cs: courseStudents) {
            courses.add(db.getByID(cs.getCourseId(), Course.class));
        }
        return courses;
    }

    @Override
    public List<Course> getAllCourses() {
        return db.getAll(Course.class);
    }

}
