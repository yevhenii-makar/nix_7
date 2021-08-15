package com.yevheniimakar.unit6.dao;

import com.yevheniimakar.unit6.domain.Student;

public interface StudentDao {

    Student getStudentById(int id);

    void createStudent(Student student);

    void deleteStudentById(int id);

    void updateStudent(Student student);

    void updateStudent(Student student, int[] courseListIds);

    Student[] getStudentListByCourseIdOrNull(int courseId);

    Student[] getAllStudents();
}
