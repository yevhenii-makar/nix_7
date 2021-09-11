package com.yevheniimakar.service.objects;

import java.util.List;


public class StudentObject extends BaseObject{

    private List<CourseObject> course;

    public List<CourseObject> getCourse() {
        return course;
    }

    public void setCourse(List<CourseObject> course) {
        this.course = course;
    }
}
