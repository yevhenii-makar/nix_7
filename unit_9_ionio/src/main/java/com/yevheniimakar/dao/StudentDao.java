package com.yevheniimakar.dao;


import com.yevheniimakar.domain.Student;

import java.util.List;


public interface StudentDao {

    Student getStudentById(int id);

    void createStudent(Student student);

    void deleteStudentById(int id);

    void updateStudent(Student student);

    void updateStudent(Student student, List<Integer> courseListIds);

    List<Student> getStudentListByCourseIdOrNull(int courseId);

    List<Student> getAllStudents();

}
