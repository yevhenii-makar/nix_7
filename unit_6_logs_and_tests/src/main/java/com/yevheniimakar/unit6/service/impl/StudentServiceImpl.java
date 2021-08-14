package com.yevheniimakar.unit6.service.impl;

import com.yevheniimakar.unit6.dao.StudentDao;
import com.yevheniimakar.unit6.dao.impl.StudentDAOImpl;
import com.yevheniimakar.unit6.domain.Student;
import com.yevheniimakar.unit6.service.CourseService;
import com.yevheniimakar.unit6.service.StudentService;
import com.yevheniimakar.unit6.service.objects.CourseObject;
import com.yevheniimakar.unit6.service.objects.StudentObject;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private CourseService courseService;

    public StudentServiceImpl() {
        this.studentDao = new StudentDAOImpl();
        this.courseService = new CourseServiceImpl();
    }

    @Override
    public StudentObject getStudentByIdWithCourseList(int id) {
        Student student = studentDao.getStudentById(id);
        StudentObject studentObject = new StudentObject();
        studentObject.setId(student.getId());
        studentObject.setName(student.getName());
        studentObject.setCourse(courseService.getCoursesListByStudentOrNull(studentObject));
        return studentObject;
    }

    @Override
    public StudentObject getStudentByIdWithoutCourseList(int id) {
        Student student = studentDao.getStudentById(id);
        StudentObject studentObject = new StudentObject();
        studentObject.setId(student.getId());
        studentObject.setName(student.getName());
        return studentObject;
    }

    @Override
    public void createStudent(StudentObject studentObject) {
        Student student = new Student();
        student.setId(studentObject.getId());
        student.setName(studentObject.getName());
        studentDao.createStudent(student);
    }

    @Override
    public void deleteStudentById(int id) {
        studentDao.deleteStudentById(id);
    }

    @Override
    public void updateStudent(StudentObject studentObject) {
        Student student = new Student();
        student.setId(studentObject.getId());
        student.setName(studentObject.getName());
        if (studentObject.getCourse() != null && studentObject.getCourse().length > 0) {
            int[] courses = new int[studentObject.getCourse().length];
            for (int i = 0; i < studentObject.getCourse().length; i++) {
                if (studentObject.getCourse()[i] != null) {
                    courses[i] = studentObject.getCourse()[i].getId();
                } else {
                    courses[i] = -1;
                }
            }
            studentDao.updateStudent(student, getFixedArray(courses));
        } else {
            studentDao.updateStudent(student);
        }
    }

    @Override
    public StudentObject[] getStudentsListByCourseOrNull(CourseObject courseObject) {
        Student[] students = studentDao.getStudentListByCourseIdOrNull(courseObject.getId());
        if (students != null) {
            StudentObject[] studentObjects = new StudentObject[students.length];
            for (int i = 0; i < students.length; i++) {
                studentObjects[i] = new StudentObject();
                studentObjects[i].setId(students[i].getId());
                studentObjects[i].setName(students[i].getName());
            }
            return studentObjects;
        } else {
            return null;
        }
    }

    @Override
    public StudentObject[] getAllStudentObject() {
        Student[] students = studentDao.getAllStudents();
        if (students != null) {
            StudentObject[] studentObjects = new StudentObject[students.length];
            for (int i = 0; i < students.length; i++) {
                studentObjects[i] = new StudentObject();
                studentObjects[i].setId(students[i].getId());
                studentObjects[i].setName(students[i].getName());
            }
            return studentObjects;
        } else {
            return null;
        }
    }

    private int[] getFixedArray(int[] intArray) {
        int[] result = intArray;
        int countNotValidCell = 0;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] < 0) {
                countNotValidCell++;
            }
        }
        if (countNotValidCell > 0) {
            result = new int[intArray.length - countNotValidCell];
            int index = 0;
            for (int i = 0; i < intArray.length; i++) {
                if (intArray[i] > 0) {
                    result[index] = intArray[i];
                    index++;
                }
            }
        }
        return result;
    }
}
