package com.yevheniimakar.unit6.service.impl.util;

import com.yevheniimakar.unit6.service.objects.CourseObject;

public class CourseObjectUtil {
    public static final String NAME = "CourseTest";

    public static CourseObject generateCourseObject(){
        CourseObject courseObject = new CourseObject();
        courseObject.setName(NAME);
        return courseObject;
    }
    public static CourseObject generateCourseObject(String name){
        CourseObject courseObject = new CourseObject();
        courseObject.setName(name);
        return courseObject;
    }
}
