package com.yevheniimakar.unit6.service.impl;

import com.yevheniimakar.unit6.dao.CourseDao;
import com.yevheniimakar.unit6.dao.impl.CourseDAOImpl;
import com.yevheniimakar.unit6.domain.Course;
import com.yevheniimakar.unit6.service.CourseService;
import com.yevheniimakar.unit6.service.StudentService;
import com.yevheniimakar.unit6.service.objects.CourseObject;
import com.yevheniimakar.unit6.service.objects.StudentObject;

public class CourseServiceImpl implements CourseService {
    CourseDao courseDao;
    StudentService studentService;

    public CourseServiceImpl() {
        this.courseDao = new CourseDAOImpl();
        this.studentService = new StudentServiceImpl();
    }


    @Override
    public CourseObject getCourseByIdWithCourses(int id) {
        Course course = courseDao.getCourseById(id);
        CourseObject courseObject = new CourseObject();
        courseObject.setId(course.getId());
        courseObject.setName(course.getName());
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
        Course course = new Course();
        course.setId(courseObject.getId());
        course.setName(courseObject.getName());
        courseDao.createCourse(course);
    }

    @Override
    public void deleteCourseById(int id) {
        courseDao.deleteCourseById(id);
    }

    @Override
    public void updateCourse(CourseObject courseObject) {
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
    }

    @Override
    public CourseObject[] getCoursesListByStudentOrNull(StudentObject student) {
        return new CourseObject[0];
    }

    @Override
    public CourseObject[] getAllCourseObject() {
        return new CourseObject[0];
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
