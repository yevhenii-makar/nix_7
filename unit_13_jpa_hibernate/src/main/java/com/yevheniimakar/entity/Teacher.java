package com.yevheniimakar.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "teachers")
public class Teacher {

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    List<Evaluation> evaluationList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String FirstName;
    @Column(nullable = false)
    private String LastName;
    @Column(nullable = false)
    private String eMail;

    public Teacher() {
        this.evaluationList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    public void addEvaluation(Evaluation evaluation, Student student) {
        evaluationList.add(evaluation);
        evaluation.setTeacher(this);
        student.addEvaluation(evaluation);
    }

}
