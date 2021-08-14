package com.yevheniimakar.unit6.db;

import com.yevheniimakar.unit6.domain.Student;

import java.util.Date;

public class StudentDB {
    private static StudentDB instance;

    private StudentDB() {}

    public static StudentDB getInstance(){
        if (instance == null) {
            instance = new StudentDB();
        }
        return instance;
    }

    private Student[] studentsDB = new Student[10];
    private int index = 0;

    public void createStudent(Student student){
        if (studentsDB[studentsDB.length-1] != null){
            increaseArray();
        }
        student.setId((int) System.currentTimeMillis());
        studentsDB[index] = student;
        index++;
    }

    public void updateStudent(Student student){
        int indexStudent = getIndexById(student.getId());
        student.setId(studentsDB[indexStudent].getId());
        student.setName(studentsDB[indexStudent].getName());
    }

    public Student getStudentById(int id){
        int indexStudent = getIndexById(id);
        return studentsDB[indexStudent];

    }

    public Student[] getAllStudent(){
        return studentsDB;
    }

    public void deleteStudentById(int id){
        int indexStudent = getIndexById(id);
        studentsDB[indexStudent]=null;
        rebuildArray(studentsDB.length);
    }

    private void increaseArray(){
        int newLength = studentsDB.length + (studentsDB.length>>1);
        rebuildArray(newLength);
    }

    private void rebuildArray(int newLength){
        Student[] newStudentDb = new Student[newLength];
        int indexCount = 0;
        for (int i = 0; i < studentsDB.length; i++) {
            if (studentsDB[i] != null){
                newStudentDb[indexCount] = studentsDB[i];
                indexCount++;
            }
        }
        studentsDB = newStudentDb;
        index = indexCount;
    }

    private int getIndexById(int id){
        for (int i = 0; i <studentsDB.length ; i++) {
            if(studentsDB[i]!=null && studentsDB[i].getId() == id){
                return i;
            }
        }
        return -1;
    }

}
