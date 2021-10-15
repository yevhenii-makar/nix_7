package com.yevheniimakar.entity;

import javax.persistence.*;


@Entity
@Table(name = "evaluations")
public class Evaluation {
    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "theme_id", referencedColumnName = "id")
    Theme theme;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int Evaluation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEvaluation() {
        return Evaluation;
    }

    public void setEvaluation(int evaluation) {
        Evaluation = evaluation;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


}
