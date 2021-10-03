package com.yevheniimakar.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name="groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @OneToMany (mappedBy = "group")
    private List<Student> studentList;

    @OneToMany (mappedBy = "group")
    private List<Occupation> occupationList;

    public Group() {
        this.studentList = new ArrayList<>();
        this.occupationList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void addStudent(Student student){
        studentList.add(student);
        student.setGroup(this);
    }



}
