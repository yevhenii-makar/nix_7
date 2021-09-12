package com.yevheniimakar.service.impl;


import com.yevheniimakar.dao.StudentDao;
import com.yevheniimakar.dao.impl.StudentDAOImpl;
import com.yevheniimakar.domain.Student;
import com.yevheniimakar.service.CourseService;
import com.yevheniimakar.service.StudentService;
import com.yevheniimakar.service.objects.CourseObject;
import com.yevheniimakar.service.objects.StudentObject;

import java.util.ArrayList;
import java.util.List;


public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private CourseService courseService;

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
            Student student = new Student();
            student.setId(studentObject.getId());
            student.setName(studentObject.getName());
            studentDao.createStudent(student);
        }
    }

    @Override
    public void deleteStudentById(int id) {
        studentDao.deleteStudentById(id);
    }

    @Override
    public void updateStudent(StudentObject studentObject) {
        if (isStudentNameValid(studentObject.getName())) {
            Student student = new Student();
            student.setId(studentObject.getId());
            student.setName(studentObject.getName());
            if (studentObject.getCourse() != null && studentObject.getCourse().size() > 0) {
                List<Integer> courses = new ArrayList<>();
                for (int i = 0; i < studentObject.getCourse().size(); i++) {
                    if (studentObject.getCourse().get(i) != null) {
                        courses.add(studentObject.getCourse().get(i).getId());
                    }
                }
                studentDao.updateStudent(student, courses);
            } else {
                studentDao.updateStudent(student);
            }
        }
    }

    @Override
    public List<StudentObject> getStudentsListByCourseOrNull(CourseObject courseObject) {
        List<Student> students = studentDao.getStudentListByCourseIdOrNull(courseObject.getId());
        if (students != null) {
            List<StudentObject> studentObjects = new ArrayList<>();
            for (int i = 0; i < students.size(); i++) {
                StudentObject so = new StudentObject();
                so.setId(students.get(i).getId());
                so.setName(students.get(i).getName());
                studentObjects.add(so);
            }
            return studentObjects;
        } else {
            return null;
        }
    }

    @Override
    public List<StudentObject> getAllStudentObject() {
        List<Student> students = studentDao.getAllStudents();
        if (students != null) {
            List<StudentObject> studentObjects = new ArrayList<>();
            for (int i = 0; i < students.size(); i++) {
                StudentObject so = new StudentObject();
                so.setId(students.get(i).getId());
                so.setName(students.get(i).getName());
                studentObjects.add(so);
            }
            return studentObjects;
        } else {
            return null;
        }
    }

    private boolean isStudentNameValid(String name) {
        return !name.matches("[^a-zA-z]");
    }

}
