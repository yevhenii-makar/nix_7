package com.yevheniimakar.unit6.logs.service.objects;

import com.yevheniimakar.unit6.logs.domain.StudentCourse;

public class CourseObject extends BaseObject {

    private StudentObject[] student;

    public StudentObject[] getStudent() {
        return student;
    }

    public void setStudent(StudentObject[] student) {
        this.student = student;
    }
}
