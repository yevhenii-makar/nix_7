package com.yevheniimakar.unit6.logs.service.impl;

import com.yevheniimakar.unit6.logs.dao.CourseDao;
import com.yevheniimakar.unit6.logs.dao.impl.CourseDAOImpl;
import com.yevheniimakar.unit6.logs.domain.Course;
import com.yevheniimakar.unit6.logs.service.CourseService;
import com.yevheniimakar.unit6.logs.service.StudentService;
import com.yevheniimakar.unit6.logs.service.objects.CourseObject;
import com.yevheniimakar.unit6.logs.service.objects.StudentObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseServiceImpl implements CourseService {
    CourseDao courseDao;
    StudentService studentService;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    public CourseServiceImpl() {
        this.courseDao = new CourseDAOImpl();

    }

    @Override
    public CourseObject getCourseByIdWithCourses(int id) {
        Course course = courseDao.getCourseById(id);
        CourseObject courseObject = new CourseObject();
        courseObject.setId(course.getId());
        courseObject.setName(course.getName());
        this.studentService = new StudentServiceImpl();
        courseObject.setStudent(studentService.getStudentsListByCourseOrNull(courseObject));
        return courseObject;
    }

    @Override
    public CourseObject getCourseByIdWithoutCourses(int id) {
        Course course = courseDao.getCourseById(id);
        CourseObject courseObject = new CourseObject();
        courseObject.setId(course.getId());
        courseObject.setName(course.getName());
        return courseObject;
    }

    @Override
    public void createCourse(CourseObject courseObject) {
        if (isCourseNameValid(courseObject.getName())) {
            LOGGER_INFO.info("create new course: " + courseObject.getName());
            Course course = new Course();
            course.setId(courseObject.getId());
            course.setName(courseObject.getName());
            courseDao.createCourse(course);
        } else {
            LOGGER_WARN.info("Entered not valid name: " + courseObject.getName() + "\non create");
        }

    }

    @Override
    public void deleteCourseById(int id) {
        LOGGER_INFO.info("delete course id: " + id);
        courseDao.deleteCourseById(id);
    }

    @Override
    public void updateCourse(CourseObject courseObject) {
        if (isCourseNameValid(courseObject.getName())) {
            LOGGER_INFO.info("update course id: " + courseObject.getId());
            Course course = new Course();
            course.setId(courseObject.getId());
            course.setName(courseObject.getName());
            if (courseObject.getStudent() != null && courseObject.getStudent().length > 0) {
                int[] students = new int[courseObject.getStudent().length];
                for (int i = 0; i < courseObject.getStudent().length; i++) {
                    if (courseObject.getStudent()[i] != null) {
                        students[i] = courseObject.getStudent()[i].getId();
                    } else {
                        students[i] = -1;
                    }
                }
                courseDao.updateCourse(course, getFixedArray(students));
            } else {
                courseDao.updateCourse(course);
            }
        } else {
            LOGGER_WARN.info("Entered not valid name: " + courseObject.getName() + "\non update");
        }
    }

    @Override
    public CourseObject[] getCoursesListByStudentOrNull(StudentObject studentObject) {
        Course[] courses = courseDao.getCoursesListByStudentIdOrNull(studentObject.getId());
        if (courses != null) {
            CourseObject[] courseObjects = new CourseObject[courses.length];
            for (int i = 0; i < courses.length; i++) {
                courseObjects[i] = new CourseObject();
                courseObjects[i].setId(courses[i].getId());
                courseObjects[i].setName(courses[i].getName());
            }
            return courseObjects;
        } else {
            LOGGER_WARN.info("courses list by student id " + studentObject.getId() + " is empty");
            return null;
        }
    }

    @Override
    public CourseObject[] getAllCourseObject() {
        Course[] courses = courseDao.getAllCourses();
        if (courses != null) {
            CourseObject[] courseObjects = new CourseObject[courses.length];
            for (int i = 0; i < courses.length; i++) {
                courseObjects[i] = new CourseObject();
                courseObjects[i].setId(courses[i].getId());
                courseObjects[i].setName(courses[i].getName());
            }
            return courseObjects;
        } else {
            LOGGER_WARN.info("courses list is empty");
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

    private boolean isCourseNameValid(String name) {
        return !name.matches("[^a-zA-z0-9\\-_/]");
    }
}
