package com.yevheniimakar.unit6.logs.service.impl;

import com.yevheniimakar.unit6.logs.service.CourseService;
import com.yevheniimakar.unit6.logs.service.StudentService;
import com.yevheniimakar.unit6.logs.service.impl.util.CourseObjectUtil;
import com.yevheniimakar.unit6.logs.service.impl.util.StudentObjectUtil;
import com.yevheniimakar.unit6.logs.service.objects.CourseObject;
import com.yevheniimakar.unit6.logs.service.objects.StudentObject;
import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseServiceImplTest {

    private final static StudentService studentservice = new StudentServiceImpl();
    private final static CourseService courseService = new CourseServiceImpl();
    private final static int STUDENTS_SIZE = 5;
    private final static int COURSE_SIZE = 10;

    @BeforeAll
    public static void setUp() {
        CourseObject [] courseObjects= courseService.getAllCourseObject();
        for (CourseObject co: courseObjects) {
            courseService.deleteCourseById(co.getId());
        }
        String variable = "I";
        for (int i = 0; i < STUDENTS_SIZE; i++) {
            StudentObject studentObject = StudentObjectUtil.generateStudentObject("test" + variable);
            studentservice.createStudent(studentObject);
            variable = variable + "I";
        }
        for (int i = 0; i < COURSE_SIZE; i++) {
            CourseObject courseObject = CourseObjectUtil.generateCourseObject("test" + i);
            courseService.createCourse(courseObject);
        }
        Assertions.assertEquals(COURSE_SIZE, courseService.getAllCourseObject().length);
    }
    @AfterAll
    public static void clearAll(){
        CourseObject [] courseObjects= courseService.getAllCourseObject();
        for (CourseObject co: courseObjects) {
            courseService.deleteCourseById(co.getId());
        }
    }

    @Test
    @Order(0)
    void getAllCourseObject() {
        CourseObject[] courseObjects = courseService.getAllCourseObject();
        Assertions.assertEquals(COURSE_SIZE, courseObjects.length);
    }

    @Test
    @Order(1)
    void updateCourse() {
        CourseObject courseObject = courseService.getAllCourseObject()[0];
        String testName = "TestUpdate";
        courseObject.setName(testName);
        courseObject.setStudent(studentservice.getAllStudentObject());
        courseService.updateCourse(courseObject);
        CourseObject courseObjectAfterUpdate = courseService.getCourseByIdWithoutCourses(courseObject.getId());
        Assertions.assertEquals(testName, courseObjectAfterUpdate.getName());
    }

    @Test
    @Order(2)
    void getCourseByIdWithCourses() {
        CourseObject courseObject = courseService.getAllCourseObject()[0];
        CourseObject courseObjectById = courseService.getCourseByIdWithCourses(courseObject.getId());
        Assertions.assertEquals(STUDENTS_SIZE, courseObjectById.getStudent().length);
    }

    @Test
    @Order(3)
    void getCourseByIdWithoutCourses() {
        CourseObject courseObject = courseService.getAllCourseObject()[0];
        CourseObject courseObjectById = courseService.getCourseByIdWithoutCourses(courseObject.getId());
        Assertions.assertEquals(null , courseObjectById.getStudent());
    }

    @Test
    @Order(4)
    void getCoursesListByStudentOrNull() {
        StudentObject studentObject = studentservice.getAllStudentObject()[0];
        CourseObject[] courseObjects = courseService.getCoursesListByStudentOrNull(studentObject);
        Assertions.assertNotEquals(null, courseObjects);
    }

    @Test
    @Order(5)
    void createCourse() {
        CourseObject courseObject = CourseObjectUtil.generateCourseObject("testCreate");
        courseService.createCourse(courseObject);
        CourseObject[] courseObjects = courseService.getAllCourseObject();
        Assertions.assertEquals(COURSE_SIZE + 1, courseObjects.length);

    }

    @Test
    @Order(6)
    void deleteCourseById() {
        CourseObject courseObject = courseService.getAllCourseObject()[0];
        courseService.deleteCourseById(courseObject.getId());
        CourseObject[] courseObjects = courseService.getAllCourseObject();
        Assertions.assertEquals(COURSE_SIZE , courseObjects.length);
    }



}