package com.yevheniimakar.service.objects;

import java.util.List;


public class CourseObject extends BaseObject {

    private List<StudentObject> student;

    public List<StudentObject> getStudent() {
        return student;
    }

    public void setStudent(List<StudentObject> student) {
        this.student = student;
    }
}
