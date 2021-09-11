package com.yevheniimakar.unit6.logs.service;


import com.yevheniimakar.unit6.logs.service.objects.CourseObject;
import com.yevheniimakar.unit6.logs.service.objects.StudentObject;

public interface StudentService {

    StudentObject getStudentByIdWithCourseList(int id);

    StudentObject getStudentByIdWithoutCourseList(int id);

    void createStudent(StudentObject studentObject);

    void deleteStudentById(int id);

    void updateStudent(StudentObject student);

    StudentObject[] getStudentsListByCourseOrNull(CourseObject CourseObject);

    StudentObject[] getAllStudentObject();

}
