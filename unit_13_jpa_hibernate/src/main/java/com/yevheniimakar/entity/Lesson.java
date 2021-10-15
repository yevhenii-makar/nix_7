package com.yevheniimakar.entity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theme_id", referencedColumnName = "id")
    Theme theme;

    @Column
    LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
