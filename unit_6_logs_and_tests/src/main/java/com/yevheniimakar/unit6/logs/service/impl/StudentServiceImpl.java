package com.yevheniimakar.unit6.logs.service.impl;

import com.yevheniimakar.unit6.logs.dao.StudentDao;
import com.yevheniimakar.unit6.logs.dao.impl.StudentDAOImpl;
import com.yevheniimakar.unit6.logs.domain.Student;
import com.yevheniimakar.unit6.logs.service.CourseService;
import com.yevheniimakar.unit6.logs.service.StudentService;
import com.yevheniimakar.unit6.logs.service.objects.CourseObject;
import com.yevheniimakar.unit6.logs.service.objects.StudentObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private CourseService courseService;
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    public StudentServiceImpl() {
        this.studentDao = new StudentDAOImpl();
    }

    @Override
    public StudentObject getStudentByIdWithCourseList(int id) {
        Student student = studentDao.getStudentById(id);
        StudentObject studentObject = new StudentObject();
        studentObject.setId(student.getId());
        studentObject.setName(student.getName());
        this.courseService = new CourseServiceImpl();
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
        if (isStudentNameValid(studentObject.getName())) {
            LOGGER_INFO.info("create new student: " + studentObject.getName());
            Student student = new Student();
            student.setId(studentObject.getId());
            student.setName(studentObject.getName());
            studentDao.createStudent(student);
        } else {
            LOGGER_WARN.info("Entered not valid name: " + studentObject.getName() + "\non create");
        }
    }

    @Override
    public void deleteStudentById(int id) {
        LOGGER_INFO.info("delete student id: " + id);
        studentDao.deleteStudentById(id);
    }

    @Override
    public void updateStudent(StudentObject studentObject) {
        if (isStudentNameValid(studentObject.getName())) {
            LOGGER_INFO.info("update student id: " + studentObject.getId());
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
        } else {
            LOGGER_WARN.info("Entered not valid name: " + studentObject.getName() + "\non update");
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
            LOGGER_WARN.info("students list by course id " + courseObject.getId() + " is empty");
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
            LOGGER_WARN.info("students list is empty");
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

    private boolean isStudentNameValid(String name) {
        return !name.matches("[^a-zA-z]");
    }
}
