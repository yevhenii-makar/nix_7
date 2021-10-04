package com.yevheniimakar.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table (name="occupations")
public class Occupation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn (name = "theme_id", referencedColumnName = "id")
    Theme theme;

    LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn (name = "teacher_id", referencedColumnName = "id")
    Teacher teacher;

    @ManyToOne
    @JoinColumn (name = "group_id", referencedColumnName = "id")
    Group group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        dateTime = dateTime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
