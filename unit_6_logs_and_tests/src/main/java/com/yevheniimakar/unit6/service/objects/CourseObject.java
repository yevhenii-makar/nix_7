package com.yevheniimakar.unit6.service.objects;

import com.yevheniimakar.unit6.domain.StudentCourse;

public class CourseObject extends BaseObject {

    private StudentObject[] student;

    public StudentObject[] getStudent() {
        return student;
    }

    public void setStudent(StudentObject[] student) {
        this.student = student;
    }
}
