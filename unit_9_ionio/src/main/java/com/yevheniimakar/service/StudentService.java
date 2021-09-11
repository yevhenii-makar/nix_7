package com.yevheniimakar.service;


import com.yevheniimakar.service.objects.CourseObject;
import com.yevheniimakar.service.objects.StudentObject;

import java.util.List;


public interface StudentService {

    StudentObject getStudentByIdWithCourseList(int id);

    StudentObject getStudentByIdWithoutCourseList(int id);

    void createStudent(StudentObject studentObject);

    void deleteStudentById(int id);

    void updateStudent(StudentObject student);

    List<StudentObject> getStudentsListByCourseOrNull(CourseObject CourseObject);

    List<StudentObject> getAllStudentObject();

}
