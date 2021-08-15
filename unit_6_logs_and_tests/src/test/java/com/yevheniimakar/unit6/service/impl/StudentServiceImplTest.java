package com.yevheniimakar.unit6.service.impl;

import com.yevheniimakar.unit6.service.CourseService;
import com.yevheniimakar.unit6.service.StudentService;
import com.yevheniimakar.unit6.service.impl.util.CourseObjectUtil;
import com.yevheniimakar.unit6.service.impl.util.StudentObjectUtil;
import com.yevheniimakar.unit6.service.objects.CourseObject;
import com.yevheniimakar.unit6.service.objects.StudentObject;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentServiceImplTest {

    private final static StudentService studentservice = new StudentServiceImpl();
    private final static CourseService courseService = new CourseServiceImpl();
    private final static int STUDENTS_SIZE = 10;
    private final static int COURSE_SIZE = 5;

    @BeforeAll
    public static void setUp() {
        String variable = "I";
        for (int i = 0; i < STUDENTS_SIZE; i++) {
            StudentObject studentObject = StudentObjectUtil.generateStudentObject("test" + variable);
            studentservice.createStudent(studentObject);
            variable = variable + "I";
        }
        for (int i = 0; i < COURSE_SIZE; i++) {
            CourseObject courseObject = CourseObjectUtil.generateCourseObject("test"+i);
            courseService.createCourse(courseObject);
        }
        Assertions.assertEquals(STUDENTS_SIZE, studentservice.getAllStudentObject().length);

    }
    @AfterAll
    public static void clearAll(){
        StudentObject[] studentObjects = studentservice.getAllStudentObject();
        for (StudentObject so: studentObjects) {
            studentservice.deleteStudentById(so.getId());
        }
    }

    @Test
    @Order(0)
    void getAllStudentObject() {
        StudentObject[] studentObjects = studentservice.getAllStudentObject();
        Assertions.assertEquals(STUDENTS_SIZE, studentObjects.length);
    }

    @Test
    @Order(3)
    void getStudentByIdWithCourseList() {
        StudentObject studentObject = studentservice.getAllStudentObject()[0];
        StudentObject studentObjectById = studentservice.getStudentByIdWithCourseList(studentObject.getId());
        Assertions.assertEquals(studentObjectById.getCourse().length, COURSE_SIZE);
    }

    @Test
    @Order(4)
    void getStudentByIdWithoutCourseList() {
        StudentObject studentObject = studentservice.getAllStudentObject()[0];
        StudentObject studentObjectById = studentservice.getStudentByIdWithoutCourseList(studentObject.getId());
        Assertions.assertEquals(studentObjectById.getCourse(), null);
    }

    @Test
    @Order(5)
    void createStudent() {
        StudentObject studentObject = StudentObjectUtil.generateStudentObject("testCreate" );
        studentservice.createStudent(studentObject);
        StudentObject[] studentObjects = studentservice.getAllStudentObject();
        Assertions.assertEquals((STUDENTS_SIZE + 1), studentObjects.length);
    }

    @Test
    @Order(7)
    void deleteStudentById() {
        StudentObject studentObject = studentservice.getAllStudentObject()[0];
        studentservice.deleteStudentById(studentObject.getId());
        StudentObject[] studentObjects = studentservice.getAllStudentObject();
        Assertions.assertEquals(STUDENTS_SIZE, studentObjects.length);
    }

    @Test
    @Order(2)
    void updateStudent() {
        StudentObject studentObject = studentservice.getAllStudentObject()[0];
        String testName = "TestUpdate";
        studentObject.setName(testName);
        studentObject.setCourse(courseService.getAllCourseObject());
        studentservice.updateStudent(studentObject);
        StudentObject studentObjectAfterUpdate = studentservice.getStudentByIdWithCourseList(studentObject.getId());
        Assertions.assertEquals(studentObjectAfterUpdate.getName(),testName);
        Assertions.assertEquals(studentObjectAfterUpdate.getCourse().length,COURSE_SIZE);

    }

    @Test
    @Order(6)
    void getStudentsListByCourseOrNull() {
        CourseObject courseObject = courseService.getAllCourseObject()[0];
        StudentObject[] studentObjects = studentservice.getStudentsListByCourseOrNull(courseObject);
        Assertions.assertNotEquals(null, studentObjects);
    }


}