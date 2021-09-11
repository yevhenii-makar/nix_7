package com.yevheniimakar.service.impl;


import com.yevheniimakar.dao.CourseDao;
import com.yevheniimakar.dao.impl.CourseDAOImpl;
import com.yevheniimakar.domain.Course;
import com.yevheniimakar.service.CourseService;
import com.yevheniimakar.service.StudentService;
import com.yevheniimakar.service.objects.CourseObject;
import com.yevheniimakar.service.objects.StudentObject;

import java.util.ArrayList;
import java.util.List;


public class CourseServiceImpl implements CourseService {
    CourseDao courseDao;
    StudentService studentService;


    public CourseServiceImpl() {
        this.courseDao = new CourseDAOImpl();

    }

    @Override
    public CourseObject getCourseByIdWithStudents(int id) {
        Course course = courseDao.getCourseById(id);
        CourseObject courseObject = new CourseObject();
        courseObject.setId(course.getId());
        courseObject.setName(course.getName());
        this.studentService = new StudentServiceImpl();
        courseObject.setStudent(studentService.getStudentsListByCourseOrNull(courseObject));
        return courseObject;
    }

    @Override
    public CourseObject getCourseByIdWithoutStudents(int id) {
        Course course = courseDao.getCourseById(id);
        CourseObject courseObject = new CourseObject();
        courseObject.setId(course.getId());
        courseObject.setName(course.getName());
        return courseObject;
    }

    @Override
    public void createCourse(CourseObject courseObject) {
        if (isCourseNameValid(courseObject.getName())) {
            Course course = new Course();
            course.setId(courseObject.getId());
            course.setName(courseObject.getName());
            courseDao.createCourse(course);
        }

    }

    @Override
    public void deleteCourseById(int id) {
        courseDao.deleteCourseById(id);
    }

    @Override
    public void updateCourse(CourseObject courseObject) {
        if (isCourseNameValid(courseObject.getName())) {
            Course course = new Course();
            course.setId(courseObject.getId());
            course.setName(courseObject.getName());
            if (courseObject.getStudent() != null && courseObject.getStudent().size() > 0) {
                int[] students = new int[courseObject.getStudent().size()];
                for (int i = 0; i < courseObject.getStudent().size(); i++) {
                    if (courseObject.getStudent().get(i) != null) {
                        students[i] = courseObject.getStudent().get(i).getId();
                    } else {
                        students[i] = -1;
                    }
                }
                courseDao.updateCourse(course);
            } else {
                courseDao.updateCourse(course);
            }
        }
    }

    @Override
    public List<CourseObject> getCoursesListByStudentOrNull(StudentObject studentObject) {
        List<Course> courses = courseDao.getCoursesListByStudentIdOrNull(studentObject.getId());
        if (courses != null) {
            List<CourseObject> courseObjects = new ArrayList<>();
            for (int i = 0; i < courses.size(); i++) {
                CourseObject co = new CourseObject();
                co.setId(courses.get(i).getId());
                co.setName(courses.get(i).getName());
                courseObjects.add(co);
            }
            return courseObjects;
        } else {
            return null;
        }
    }

    @Override
    public List<CourseObject> getAllCourseObject() {
        List<Course> courses = courseDao.getAllCourses();
        if (courses != null) {
            List<CourseObject> courseObjects = new ArrayList<>();
            for (int i = 0; i < courses.size(); i++) {
                CourseObject co = new CourseObject();
                co.setId(courses.get(i).getId());
                co.setName(courses.get(i).getName());
                courseObjects.add(co);
            }
            return courseObjects;
        } else {
            return null;
        }
    }



    private boolean isCourseNameValid(String name) {
        return !name.matches("[^a-zA-z0-9\\-_/]");
    }
}
