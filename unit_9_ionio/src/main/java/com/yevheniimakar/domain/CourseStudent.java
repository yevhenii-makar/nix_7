package com.yevheniimakar.domain;

import com.yevheniimakar.annotation.CsvDb;


@CsvDb (tableName = "coursstudent")
public class CourseStudent {

    private int StudentId;
    private int CourseId;

    public CourseStudent() {

    }

    public CourseStudent(int studentId, int courseId) {
        StudentId = studentId;
        CourseId = courseId;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }

}
