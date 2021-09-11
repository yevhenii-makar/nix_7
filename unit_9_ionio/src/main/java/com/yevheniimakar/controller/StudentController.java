package com.yevheniimakar.controller;

import com.yevhenii.makar.ConsoleReader;
import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;
import com.yevheniimakar.service.CourseService;
import com.yevheniimakar.service.StudentService;
import com.yevheniimakar.service.impl.CourseServiceImpl;
import com.yevheniimakar.service.impl.StudentServiceImpl;
import com.yevheniimakar.service.objects.CourseObject;
import com.yevheniimakar.service.objects.StudentObject;

import java.util.ArrayList;
import java.util.List;


@Task (taskName = "Student CRUD", order = 0)
public class StudentController {

    private StudentService studentService = new StudentServiceImpl();
    private CourseService courseService = new CourseServiceImpl();


    @RunTask (runTaskName = "Create student", order = 0)
    public void createStudent() {
        System.out.println("enter student name");
        String studentName = ConsoleReader.getStringFromConsole();
        StudentObject student = new StudentObject();
        student.setName(studentName);
        studentService.createStudent(student);
    }


    @RunTask (runTaskName = "Delete student", order = 1)
    public void deleteStudent() {
        List<StudentObject> studentObjects = studentService.getAllStudentObject();
        for (int i = 0; i < studentObjects.size(); i++) {
            System.out.println("id = " + studentObjects.get(i).getId() + "name = " + studentObjects.get(i).getName());
        }
        System.out.print("enter student id for delete:");
        int id = ConsoleReader.integerReader();
        studentService.deleteStudentById(id);
    }


    @RunTask (runTaskName = "Update student", order = 2)
    public void updateStudent() {
        List<StudentObject> studentObjects = studentService.getAllStudentObject();

        for (int i = 0; i < studentObjects.size(); i++) {
            System.out.println("id = " + studentObjects.get(i).getId() + "name = " + studentObjects.get(i).getName());
        }
        System.out.print("enter student id for update:");
        int id = ConsoleReader.integerReader();
        StudentObject student = studentService.getStudentByIdWithoutCourseList(id);
        System.out.print("enter new course name");
        student.setName(ConsoleReader.getStringFromConsole());
        studentService.updateStudent(student);
    }

    @RunTask (runTaskName = "Read student", order = 3)
    public void getStudent() {
        List<StudentObject> studentObjects = studentService.getAllStudentObject();
        for (int i = 0; i < studentObjects.size(); i++) {
            System.out.println("id = " + studentObjects.get(i).getId() + "name = " + studentObjects.get(i).getName());
        }
        System.out.print("enter student id");
        int id = ConsoleReader.integerReader();
        StudentObject studentObject = studentService.getStudentByIdWithCourseList(id);
        System.out.println("Student id = " + studentObject.getId() + " course name = " + studentObject.getName());
        if (studentObject.getCourse() != null) {
            System.out.println("course list on course:");
            for (int i = 0; i < studentObject.getCourse().size(); i++) {
                System.out.println("id = " + studentObject.getCourse().get(i).getId() + "name = " + studentObject.getCourse().get(i).getName());
            }
        }
    }

    @RunTask (runTaskName = "Add course to student", order = 4)
    public void addCourseToStudent() {
        List<StudentObject> studentObjects = studentService.getAllStudentObject();
        for (int i = 0; i < studentObjects.size(); i++) {
            System.out.println("id = " + studentObjects.get(i).getId() + "name = " + studentObjects.get(i).getName());
        }
        System.out.print("enter student id");
        int id = ConsoleReader.integerReader();
        StudentObject studentObject = studentService.getStudentByIdWithCourseList(id);
        System.out.println("Student id = " + studentObject.getId() + " course name = " + studentObject.getName());
        if (studentObject.getCourse() != null) {
            System.out.println("course list on course:");
            for (int i = 0; i < studentObject.getCourse().size(); i++) {
                System.out.println("id = " + studentObject.getCourse().get(i).getId() + "name = " + studentObject.getCourse().get(i).getName());
            }
        }
        List<CourseObject> courseObjects = courseService.getAllCourseObject();
        System.out.println("all course list");
        for (int i = 0; i < courseObjects.size(); i++) {
            System.out.println("id = " + courseObjects.get(i).getId() + "name = " + courseObjects.get(i).getName());
        }
        System.out.print("enter id to add:");
        int courseId = ConsoleReader.integerReader();
        boolean isNotContainCourse = true;
        if (studentObject.getCourse() != null) {
            for (int i = 0; i < studentObject.getCourse().size(); i++) {
                if (studentObject.getCourse().get(i).getId() == courseId) {
                    isNotContainCourse = false;
                    break;
                }
            }
            if (isNotContainCourse) {

                studentObject.getCourse().add(courseService.getCourseByIdWithoutStudents(courseId));
                studentService.updateStudent(studentObject);
            }
        } else {
            List<CourseObject> courseObjects1 = new ArrayList<>();
            courseObjects1.add(courseService.getCourseByIdWithoutStudents(courseId));
            studentObject.setCourse(courseObjects1);
            studentService.updateStudent(studentObject);
        }
    }

}
