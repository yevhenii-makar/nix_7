package com.yevheniimakar.unit6.service.impl.util;

import com.yevheniimakar.unit6.service.objects.StudentObject;

public class StudentObjectUtil {
    public static final String NAME = "StudentObjectTest";

    public static StudentObject generateStudentObject(){
        StudentObject studentObject = new StudentObject();
        studentObject.setName(NAME);
        return studentObject;
    }
    public static StudentObject generateStudentObject(String name){
        StudentObject student = new StudentObject();
        student.setName(name);
        return student;
    }
}
