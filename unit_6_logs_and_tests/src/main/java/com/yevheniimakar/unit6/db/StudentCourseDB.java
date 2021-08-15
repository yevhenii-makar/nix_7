package com.yevheniimakar.unit6.db;


import com.yevheniimakar.unit6.domain.StudentCourse;

public class StudentCourseDB {

    private static StudentCourseDB instance;

    private StudentCourseDB() {
    }

    public static StudentCourseDB getInstance() {
        if (instance == null) {
            instance = new StudentCourseDB();
        }
        return instance;
    }

    private StudentCourse[] studentCoursesDB = new StudentCourse[10];
    private int index = 0;

    public void createStudentCourse(int studentId, int courseId) {
        if (studentCoursesDB[studentCoursesDB.length - 1] != null) {
            increaseArray();
        }
        studentCoursesDB[index] = new StudentCourse(studentId, courseId);
        index++;
    }

    public void deleteStudentCourse(int studentId, int courseId) {
        for (int i = 0; i < studentCoursesDB.length; i++) {
            if (studentCoursesDB[i].getStudentId() == studentId && studentCoursesDB[i].getCourseId() == courseId) {
                studentCoursesDB[i] = null;
            }
        }
        rebuildArray(studentCoursesDB.length);
    }

    public int[] getCourseIDsByStudentIDorNull(int studentId) {
        int count = 0;
        for (int i = 0; i < studentCoursesDB.length; i++) {
            if (studentCoursesDB[i] != null && studentCoursesDB[i].getStudentId() == studentId) {
                count++;
            }
        }
        int[] courseIDs = null;
        int indexCount = 0;
        if (count > 0) {
            courseIDs = new int[count];
            for (int i = 0; i < studentCoursesDB.length; i++) {
                if (studentCoursesDB[i] != null && studentCoursesDB[i].getStudentId() == studentId) {
                    courseIDs[indexCount] = studentCoursesDB[i].getCourseId();
                    indexCount++;
                }
            }
        }
        return courseIDs;
    }

    public int[] getStudentIDsByCourseIDorNull(int coursesId) {
        int count = 0;
        int[] courseIDs = null;
        int indexCount = 0;
        for (int i = 0; i < studentCoursesDB.length; i++) {
            if (studentCoursesDB[i] != null && studentCoursesDB[i].getCourseId() == coursesId) {
                count++;
            }
        }
        if (count > 0) {
            courseIDs = new int[count];
            for (StudentCourse student : studentCoursesDB) {
                if (student.getCourseId() == coursesId) {
                    courseIDs[indexCount] = student.getStudentId();
                    indexCount++;
                    if (indexCount == count) {
                        break;
                    }
                }
            }
        }
        return courseIDs;
    }

    public void updateListCoursesByStudentId(int studentId, int[] coursesList) {
        int courseIndex = coursesList.length;
        for (int i = 0; i < studentCoursesDB.length; i++) {
            if (studentCoursesDB[i] != null && studentCoursesDB[i].getStudentId() == studentId) {
                int indexInCoursesList = getIndexById(studentCoursesDB[i].getCourseId(), coursesList);
                if (indexInCoursesList != -1) {
                    coursesList[indexInCoursesList] = -1;
                    courseIndex--;
                } else {
                    studentCoursesDB[i] = null;
                }
            }
        }
        if (courseIndex > 0) {
            for (int j = 0; j < coursesList.length; j++) {
                if (coursesList[j] != -1) {
                    createStudentCourse(studentId, coursesList[j]);
                }
            }
        }
        rebuildArray(studentCoursesDB.length);
    }

    public void updateListStudentsByCoursetId(int courseId, int[] studentsList) {
        int studentIndex = studentsList.length;
        for (int i = 0; i < studentCoursesDB.length; i++) {
            if (studentCoursesDB[i] != null && studentCoursesDB[i].getStudentId() == courseId) {
                int indexInCoursesList = getIndexById(studentCoursesDB[i].getCourseId(), studentsList);
                if (indexInCoursesList != -1) {
                    studentsList[indexInCoursesList] = -1;
                    studentIndex--;
                } else {
                    studentCoursesDB[i] = null;
                }
            }
        }
        if (studentIndex > 0) {
            for (int j = 0; j < studentsList.length; j++) {
                if (studentsList[j] != -1) {
                    createStudentCourse(studentsList[j], courseId);
                }
            }
        }
        rebuildArray(studentCoursesDB.length);
    }

    private void increaseArray() {
        int newLength = studentCoursesDB.length + (studentCoursesDB.length >> 1);
        rebuildArray(newLength);
    }

    private void rebuildArray(int newLength) {
        StudentCourse[] newStudentCourseDb = new StudentCourse[newLength];
        int increment = 0;
        for (int i = 0; i < studentCoursesDB.length; i++) {
            if (studentCoursesDB[i] != null) {
                newStudentCourseDb[increment] = studentCoursesDB[i];
                increment++;
            }
        }
        studentCoursesDB = newStudentCourseDb;
        index = increment;
    }

    private int getIndexById(int id, int[] ids) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == id) {
                return i;
            }
        }
        return -1;
    }
}
