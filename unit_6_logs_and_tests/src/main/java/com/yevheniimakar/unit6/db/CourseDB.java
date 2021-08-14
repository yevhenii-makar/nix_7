package com.yevheniimakar.unit6.db;

import com.yevheniimakar.unit6.domain.Course;

public class CourseDB {

    private static CourseDB instance;

    private CourseDB() {}

    public static CourseDB getInstance(){
        if (instance == null) {
            instance = new CourseDB();
        }
        return instance;
    }

    private Course[] coursesDB = new Course[10];
    private int index = 0;

    public void createCourse(Course course){
        if (coursesDB[coursesDB.length-1] != null){
            increaseArray();
        }
        coursesDB[index] = course;
        index++;
    }

    public void updateCourse(Course course){
        int indexCourse = getIndexById(course.getId());
        course.setId(coursesDB[indexCourse].getId());
        course.setName(coursesDB[indexCourse].getName());
    }

    public Course getCourseById(int id){
        int indexCourse = getIndexById(id);
        return coursesDB[indexCourse];
    }

    public Course[] getAllCourse(){
        return coursesDB;
    }

    public void deleteCourseById(int id){
        int indexCourse = getIndexById(id);
        coursesDB[indexCourse] = null;
        rebuildArray(coursesDB.length);
    }

    private void increaseArray(){
        int newLength = coursesDB.length + (coursesDB.length>>1);
        rebuildArray(newLength);
    }

    private void rebuildArray(int newLength){
        Course[] newCourseDb = new Course[newLength];
        int indexCount = 0;
        for (int i = 0; i < coursesDB.length; i++) {
            if (coursesDB[i] != null){
                newCourseDb[indexCount] = coursesDB[i];
                indexCount++;
            }
        }
        coursesDB = newCourseDb;
        index = indexCount;
    }

    private int getIndexById(int id){
        for (int i = 0; i <coursesDB.length ; i++) {
            if(coursesDB[i]!=null && coursesDB[i].getId() == id){
                return i;
            }
        }
        return -1;
    }
}
