package com.yevheniimakar.unit6.logs.service.objects;

public class StudentObject extends BaseObject{

    private CourseObject[] course;

    public CourseObject[] getCourse() {
        return course;
    }

    public void setCourse(CourseObject[] course) {
        this.course = course;
    }
}
