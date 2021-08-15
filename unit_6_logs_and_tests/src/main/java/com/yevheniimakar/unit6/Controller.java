package com.yevheniimakar.unit6;

import com.yevheniimakar.unit6.service.CourseService;
import com.yevheniimakar.unit6.service.StudentService;
import com.yevheniimakar.unit6.service.impl.CourseServiceImpl;
import com.yevheniimakar.unit6.service.impl.StudentServiceImpl;
import com.yevheniimakar.unit6.service.objects.CourseObject;
import com.yevheniimakar.unit6.service.objects.StudentObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    private StudentService studentService = new StudentServiceImpl();
    private CourseService courseService = new CourseServiceImpl();

    public void startController() {
        creatDB();
        boolean isContinue = true;
        while (isContinue) {
            System.out.println(" 1 - create student");
            System.out.println(" 2 - get student");
            System.out.println(" 3 - update student");
            System.out.println(" 4 - add course to student");
            System.out.println(" 5 - delete student");
            System.out.println(" 6 - create course");
            System.out.println(" 7 - get course");
            System.out.println(" 8 - update course");
            System.out.println(" 9 - add student to course");
            System.out.println(" 10 - delete course");
            System.out.println(" 11 - exit");
            System.out.println("enter your choice: ");
            int choice = integerReader();
            switch (choice) {
                case 1: {
                    createStudent();
                }
                break;
                case 2: {
                    getStudent();
                }
                break;
                case 3: {
                    updateStudent();
                }
                break;
                case 4: {
                    addCourseToStudent();
                }
                break;
                case 5: {
                    deleteStudent();
                }
                break;
                case 6: {
                    createCourse();
                }
                break;
                case 7: {
                    getCourse();
                }
                break;
                case 8: {
                    updateCourse();
                }
                break;
                case 9: {
                    addStudentToCourse();
                }
                break;
                case 10: {
                    deleteCourse();
                }
                break;
                case 11: {
                    isContinue = false;
                }
                break;
            }
        }
    }

    public void createStudent() {
        System.out.println("enter student name");
        String studentName = getStringFromConsole();
        StudentObject student = new StudentObject();
        student.setName(studentName);
        studentService.createStudent(student);
    }

    public void createCourse() {
        System.out.println("enter course name");
        String studentName = getStringFromConsole();
        CourseObject course = new CourseObject();
        course.setName(studentName);
        courseService.createCourse(course);
    }

    public void deleteStudent() {
        StudentObject[] studentObjects = studentService.getAllStudentObject();
        for (int i = 0; i < studentObjects.length; i++) {
            System.out.println("id = " + studentObjects[i].getId() + "name = " + studentObjects[i].getName());
        }
        System.out.print("enter student id for delete:");
        int id = integerReader();
        studentService.deleteStudentById(id);
    }

    public void deleteCourse() {
        CourseObject[] courseObjects = courseService.getAllCourseObject();
        for (int i = 0; i < courseObjects.length; i++) {
            System.out.println("id = " + courseObjects[i].getId() + "name = " + courseObjects[i].getName());
        }
        System.out.println("enter course id for delete");
        int id = integerReader();
        courseService.deleteCourseById(id);
    }

    public void updateStudent() {
        StudentObject[] studentObjects = studentService.getAllStudentObject();

        for (int i = 0; i < studentObjects.length; i++) {
            System.out.println("id = " + studentObjects[i].getId() + "name = " + studentObjects[i].getName());
        }
        System.out.print("enter student id for update:");
        int id = integerReader();
        StudentObject student = studentService.getStudentByIdWithoutCourseList(id);
        System.out.print("enter new course name");
        student.setName(getStringFromConsole());
        studentService.updateStudent(student);
    }

    public void updateCourse() {
        CourseObject[] courseObjects = courseService.getAllCourseObject();
        for (int i = 0; i < courseObjects.length; i++) {
            System.out.println("id = " + courseObjects[i].getId() + "name = " + courseObjects[i].getName());
        }
        System.out.println("enter course id for update");
        int id = integerReader();
        CourseObject courseObject = courseService.getCourseByIdWithoutCourses(id);
        System.out.print("enter new course name");
        courseObject.setName(getStringFromConsole());
        courseService.updateCourse(courseObject);
    }

    public void getStudent() {
        StudentObject[] studentObjects = studentService.getAllStudentObject();
        for (int i = 0; i < studentObjects.length; i++) {
            System.out.println("id = " + studentObjects[i].getId() + "name = " + studentObjects[i].getName());
        }
        System.out.print("enter student id");
        int id = integerReader();
        StudentObject studentObject = studentService.getStudentByIdWithCourseList(id);
        System.out.println("Student id = " + studentObject.getId() + " course name = " + studentObject.getName());
        if (studentObject.getCourse() != null) {
            System.out.println("course list on course:");
            for (int i = 0; i < studentObject.getCourse().length; i++) {
                System.out.println("id = " + studentObject.getCourse()[i].getId() + "name = " + studentObject.getCourse()[i].getName());
            }
        }
    }

    public void getCourse() {
        CourseObject[] courseObjects = courseService.getAllCourseObject();
        for (int i = 0; i < courseObjects.length; i++) {
            System.out.println("id = " + courseObjects[i].getId() + "name = " + courseObjects[i].getName());
        }
        System.out.println("enter course id ");
        int id = integerReader();
        CourseObject courseObject = courseService.getCourseByIdWithCourses(id);
        System.out.println("Course id = " + courseObject.getId() + " course name = " + courseObject.getName());
        if (courseObject.getStudent() != null) {
            System.out.println("student list on course:");
            for (int i = 0; i < courseObject.getStudent().length; i++) {
                System.out.println("id = " + courseObject.getStudent()[i].getId() + "name = " + courseObject.getStudent()[i].getName());
            }
        }
    }

    public void addCourseToStudent() {
        StudentObject[] studentObjects = studentService.getAllStudentObject();
        for (int i = 0; i < studentObjects.length; i++) {
            System.out.println("id = " + studentObjects[i].getId() + "name = " + studentObjects[i].getName());
        }
        System.out.print("enter student id");
        int id = integerReader();
        StudentObject studentObject = studentService.getStudentByIdWithCourseList(id);
        System.out.println("Student id = " + studentObject.getId() + " course name = " + studentObject.getName());
        if (studentObject.getCourse() != null) {
            System.out.println("course list on course:");
            for (int i = 0; i < studentObject.getCourse().length; i++) {
                System.out.println("id = " + studentObject.getCourse()[i].getId() + "name = " + studentObject.getCourse()[i].getName());
            }
        }
        CourseObject[] courseObjects = courseService.getAllCourseObject();
        System.out.println("all course list");
        for (int i = 0; i < courseObjects.length; i++) {
            System.out.println("id = " + courseObjects[i].getId() + "name = " + courseObjects[i].getName());
        }
        System.out.print("enter id to add:");
        int courseId = integerReader();
        boolean isNotContainCourse = true;
        CourseObject[] courseObjects1;
        if (studentObject.getCourse() != null) {
            for (int i = 0; i < studentObject.getCourse().length; i++) {
                if (studentObject.getCourse()[i].getId() == courseId) {
                    isNotContainCourse = false;
                    break;
                }
            }
            if (isNotContainCourse) {
                courseObjects1 = new CourseObject[studentObject.getCourse().length + 1];
                for (int i = 0; i < studentObject.getCourse().length; i++) {
                    courseObjects1[i] = studentObject.getCourse()[i];
                }
                courseObjects1[courseObjects1.length - 1] = courseService.getCourseByIdWithoutCourses(courseId);
                studentObject.setCourse(courseObjects1);
                studentService.updateStudent(studentObject);
            }
        } else {
            courseObjects1 = new CourseObject[1];
            courseObjects1[0] = courseService.getCourseByIdWithoutCourses(courseId);
            studentObject.setCourse(courseObjects1);
            studentService.updateStudent(studentObject);
        }
    }

    public void addStudentToCourse() {
        CourseObject[] courseObjects = courseService.getAllCourseObject();
        for (int i = 0; i < courseObjects.length; i++) {
            System.out.println("id = " + courseObjects[i].getId() + "name = " + courseObjects[i].getName());
        }
        System.out.println("enter course id ");
        int id = integerReader();
        CourseObject courseObject = courseService.getCourseByIdWithCourses(id);
        System.out.println("Course id = " + courseObject.getId() + " course name = " + courseObject.getName());
        if (courseObject.getStudent() != null) {
            System.out.println("student list on course:");
            for (int i = 0; i < courseObject.getStudent().length; i++) {
                System.out.println("id = " + courseObject.getStudent()[i].getId() + "name = " + courseObject.getStudent()[i].getName());
            }
        }
        StudentObject[] studentObjects = studentService.getAllStudentObject();
        System.out.println("all students list");
        for (int i = 0; i < studentObjects.length; i++) {
            System.out.println("id = " + studentObjects[i].getId() + "name = " + studentObjects[i].getName());
        }
        System.out.print("enter id to add:");
        int studentId = integerReader();
        boolean isNotContainsStudent = true;
        if (courseObject.getStudent() != null) {
            for (int i = 0; i < courseObject.getStudent().length; i++) {
                if (courseObject.getStudent()[i].getId() == studentId) {
                    isNotContainsStudent = false;
                    break;
                }
            }
            if (isNotContainsStudent) {
                StudentObject[] studentObjects1 = new StudentObject[courseObject.getStudent().length + 1];
                for (int i = 0; i < courseObject.getStudent().length; i++) {
                    studentObjects1[i] = courseObject.getStudent()[i];
                }
                studentObjects1[studentObjects1.length - 1] = studentService.getStudentByIdWithoutCourseList(studentId);
                courseObject.setStudent(studentObjects1);
                courseService.updateCourse(courseObject);
            }
        } else {
            StudentObject[] studentObjects1 = new StudentObject[1];
            studentObjects1[0] = studentService.getStudentByIdWithoutCourseList(studentId);
            courseObject.setStudent(studentObjects1);
            courseService.updateCourse(courseObject);
        }
    }

    private int integerReader() {
        String choice = getStringFromConsole().replaceAll("[ \\t\\n\\x0B\\f\\r]", "").replace("[^0-9]", "");
        if (choice.length() > 0) {
            return Integer.parseInt(choice);
        }
        return 0;
    }

    private String getStringFromConsole() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void creatDB() {
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
