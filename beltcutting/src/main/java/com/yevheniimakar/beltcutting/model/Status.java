package com.yevheniimakar.beltcutting.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "statuses")
public class Status {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "status")
    private List<Task> cards;


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

    public List<Task> getCards() {
        return cards;
    }

    public void setCards(List<Task> cards) {
        this.cards = cards;
    }
}
