package com.yevheniimakar.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LessonDTO {

    private String theme;
    private LocalDateTime dateTime;
    private String teacherFirstName;
    private String teacherLastName;
    private String groupName;
    private Long id;

    public LessonDTO(Long id, String theme, LocalDateTime dateTime, String teacherFirstName, String teacherLastName, String groupName) {
        this.id = id;
        this.theme = theme;
        this.dateTime = dateTime;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Next class" + " teacher: " + teacherFirstName + " " + teacherLastName
                + "\n" + " group: " + groupName
                + "\n" + " date: " + dateTime.format(DateTimeFormatter.ofPattern("M/d/yyyy"))
                + "\n" + " time: " + dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                + "\n" + " theme: " + theme;
    }

}
