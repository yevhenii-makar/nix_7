package com.yevheniimakar.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    List<Operation> operations;

    public Category(){
        this.operations = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addOperation(Operation operation){
        operations.add(operation);
        operation.setCategory(this);
    }

}
