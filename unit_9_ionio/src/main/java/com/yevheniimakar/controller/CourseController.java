package com.yevheniimakar.controller;

import com.yevheniimakar.service.StudentService;

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

@Task (taskName = "Course CRUD", order = 1)
public class CourseController {

    private StudentService studentService = new StudentServiceImpl();
    private CourseService courseService = new CourseServiceImpl();



    @RunTask (runTaskName = "Create course", order = 0)
    public void createCourse() {
        System.out.println("enter course name");
        String studentName = ConsoleReader.getStringFromConsole();
        CourseObject course = new CourseObject();
        course.setName(studentName);
        courseService.createCourse(course);
    }


    @RunTask (runTaskName = "Delete course", order = 1)
    public void deleteCourse() {
        List<CourseObject> courseObjects = courseService.getAllCourseObject();
        for (int i = 0; i < courseObjects.size(); i++) {
            System.out.println("id = " + courseObjects.get(i).getId() + "name = " + courseObjects.get(i).getName());
        }
        System.out.println("enter course id for delete");
        int id =ConsoleReader.integerReader();
        courseService.deleteCourseById(id);
    }


    @RunTask (runTaskName = "Update course", order = 2)
    public void updateCourse() {
        List<CourseObject> courseObjects = courseService.getAllCourseObject();
        for (int i = 0; i < courseObjects.size(); i++) {
            System.out.println("id = " + courseObjects.get(i).getId() + "name = " + courseObjects.get(i).getName());
        }
        System.out.println("enter course id for update");
        int id =ConsoleReader.integerReader();
        CourseObject courseObject = courseService.getCourseByIdWithoutStudents(id);
        System.out.print("enter new course name");
        courseObject.setName(ConsoleReader.getStringFromConsole());
        courseService.updateCourse(courseObject);
    }


    @RunTask (runTaskName = "Read course", order = 3)
    public void getCourse() {
        List<CourseObject> courseObjects = courseService.getAllCourseObject();
        for (int i = 0; i < courseObjects.size(); i++) {
            System.out.println("id = " + courseObjects.get(i).getId() + "name = " + courseObjects.get(i).getName());
        }
        System.out.println("enter course id ");
        int id =ConsoleReader.integerReader();
        CourseObject courseObject = courseService.getCourseByIdWithStudents(id);
        System.out.println("Course id = " + courseObject.getId() + " course name = " + courseObject.getName());
        if (courseObject.getStudent() != null) {
            System.out.println("student list on course:");
            for (int i = 0; i < courseObject.getStudent().size(); i++) {
                System.out.println("id = " + courseObject.getStudent().get(i).getId() + "name = " + courseObject.getStudent().get(i).getName());
            }
        }
    }

    @RunTask (runTaskName = "Add student to course", order = 4)
    public void addStudentToCourse() {
        List<CourseObject> courseObjects = courseService.getAllCourseObject();
        for (int i = 0; i < courseObjects.size(); i++) {
            System.out.println("id = " + courseObjects.get(i).getId() + "name = " + courseObjects.get(i).getName());
        }
        System.out.println("enter course id ");
        int id =ConsoleReader.integerReader();
        CourseObject courseObject = courseService.getCourseByIdWithStudents(id);
        System.out.println("Course id = " + courseObject.getId() + " course name = " + courseObject.getName());
        if (courseObject.getStudent() != null) {
            System.out.println("student list on course:");
            for (int i = 0; i < courseObject.getStudent().size(); i++) {
                System.out.println("id = " + courseObject.getStudent().get(i).getId() + "name = " + courseObject.getStudent().get(i).getName());
            }
        }
        List<StudentObject> studentObjects = studentService.getAllStudentObject();
        System.out.println("all students list");
        for (int i = 0; i < studentObjects.size(); i++) {
            System.out.println("id = " + studentObjects.get(i).getId() + "name = " + studentObjects.get(i).getName());
        }
        System.out.print("enter id to add:");
        int studentId = ConsoleReader.integerReader();
        boolean isNotContainsStudent = true;
        if (courseObject.getStudent() != null) {
            for (int i = 0; i < courseObject.getStudent().size(); i++) {
                if (courseObject.getStudent().get(i).getId() == studentId) {
                    isNotContainsStudent = false;
                    break;
                }
            }
            if (isNotContainsStudent) {

                courseObject.getStudent().add(studentService.getStudentByIdWithoutCourseList(studentId));
                courseService.updateCourse(courseObject);
            }
        } else {
            List<StudentObject> studentObjects1 = new ArrayList<>();
            studentObjects1.add(studentService.getStudentByIdWithoutCourseList(studentId));
            courseObject.setStudent(studentObjects1);
            courseService.updateCourse(courseObject);
        }
    }



    public void creatDB() {
        if (studentService.getAllStudentObject().size()==0 || courseService.getAllCourseObject().size() == 0) {
            String name = "test";
            String postfix = "I";
            for (int i = 0; i < 10; i++) {
                StudentObject studentObject = new StudentObject();
                studentObject.setName(name + postfix);
                studentService.createStudent(studentObject);
                CourseObject courseObject = new CourseObject();
                courseObject.setName(name + i);
                courseService.createCourse(courseObject);
                postfix += "I";
            }
        }
    }

}


