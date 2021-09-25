package com.yevheniimakar.dao.impl;


import com.yevheniimakar.config.DbCsvConfig;
import com.yevheniimakar.dao.StudentDao;
import com.yevheniimakar.domain.CourseStudent;
import com.yevheniimakar.domain.Student;

import java.util.ArrayList;
import java.util.List;


public class StudentDAOImpl implements StudentDao {

    DbCsvConfig<Student> db = new DbCsvConfig<>();
    DbCsvConfig<CourseStudent> dbT = new DbCsvConfig();


    @Override
    public Student getStudentById(int id) {
        return db.getByID(id, Student.class);
    }

    @Override
    public void createStudent(Student student) {
        db.add(student);
    }

    @Override
    public void deleteStudentById(int id) {
        db.delete(this.getStudentById(id));
    }

    @Override
    public void updateStudent(Student student) {
        db.update(student);
    }

    @Override
    public void updateStudent(Student student, List<Integer> courseListIds) {
        db.update(student);
        List<CourseStudent> studentId = dbT.getByField("" + student.getId(), "studentId", CourseStudent.class);
        for (CourseStudent course_student : studentId) {
            dbT.delete(course_student);
        }
        for (int i : courseListIds) {
            dbT.add(new CourseStudent(i, student.getId()));
        }
    }

    @Override
    public List<Student> getStudentListByCourseIdOrNull(int courseId) {
        List<CourseStudent> courseStudents = dbT.getByField("" + courseId, "courseId", CourseStudent.class);
        List<Student> students = new ArrayList<>();
        for (CourseStudent cs : courseStudents) {
            students.add(db.getByID(cs.getStudentId(), Student.class));
        }
        return students;
    }

    @Override
    public List<Student> getAllStudents() {
        return db.getAll(Student.class);
    }

}


