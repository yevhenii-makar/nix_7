package com.yevheniimakar.unit6.logs.dao.impl;

import com.yevheniimakar.unit6.logs.dao.StudentDao;
import com.yevheniimakar.unit6.logs.db.StudentCourseDB;
import com.yevheniimakar.unit6.logs.db.StudentDB;
import com.yevheniimakar.unit6.logs.domain.Student;

public class StudentDAOImpl implements StudentDao {
    private StudentDB studentDB = StudentDB.getInstance();
    private StudentCourseDB studentCourseDB = StudentCourseDB.getInstance();

    @Override
    public Student getStudentById(int id) {
        return studentDB.getStudentById(id);
    }

    @Override
    public void createStudent(Student student) {
        studentDB.createStudent(student);
    }

    @Override
    public void deleteStudentById(int id) {
        studentDB.deleteStudentById(id);
        studentCourseDB.updateListCoursesByStudentId(id, new int[0]);
    }

    @Override
    public void updateStudent(Student student) {
        studentDB.updateStudent(student);
    }

    @Override
    public void updateStudent(Student student, int[] courseListIds) {
        studentDB.updateStudent(student);
        studentCourseDB.updateListCoursesByStudentId(student.getId(), courseListIds);
    }

    @Override
    public Student[] getStudentListByCourseIdOrNull(int courseId) {
        int[] studentIdDsList = studentCourseDB.getStudentIDsByCourseIDorNull(courseId);
        Student[] studentsList = null;
        if (studentIdDsList != null && studentIdDsList.length > 0) {
            studentsList = new Student[studentIdDsList.length];
            for (int i = 0; i < studentIdDsList.length; i++) {
                studentsList[i] = studentDB.getStudentById(studentIdDsList[i]);
            }
        }
        return studentsList;
    }

    @Override
    public Student[] getAllStudents() {
        return studentDB.getAllStudent();
    }
}
