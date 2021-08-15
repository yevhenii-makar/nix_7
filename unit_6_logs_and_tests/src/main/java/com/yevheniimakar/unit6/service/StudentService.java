package com.yevheniimakar.unit6.service;


import com.yevheniimakar.unit6.service.objects.CourseObject;
import com.yevheniimakar.unit6.service.objects.StudentObject;

public interface StudentService {

    StudentObject getStudentByIdWithCourseList(int id);

    StudentObject getStudentByIdWithoutCourseList(int id);

    void createStudent(StudentObject studentObject);

    void deleteStudentById(int id);

    void updateStudent(StudentObject student);

    StudentObject[] getStudentsListByCourseOrNull(CourseObject CourseObject);

    StudentObject[] getAllStudentObject();

}
