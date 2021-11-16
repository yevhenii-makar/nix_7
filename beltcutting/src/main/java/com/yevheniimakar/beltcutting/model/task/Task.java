package com.yevheniimakar.beltcutting.model.task;

import com.yevheniimakar.beltcutting.model.complectation.Complectation;
import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    private Long id;
    private String name;
    private String message;
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updated_at;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TaskStatus status = TaskStatus.CREATED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private BeltCuttingUser beltCuttingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    private Integer count;

    @OneToMany(mappedBy = "task" )
    private List<Complectation> complectationList;

    public Task() {
        this.complectationList = new ArrayList<>();
    }

    public Task(Long id,
                String name,
                String message,
                OffsetDateTime created_at,
                OffsetDateTime updated_at,
                TaskStatus status,
                BeltCuttingUser beltCuttingUser,
                Card card,
                Integer count,
                List<Complectation> complectationList) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.beltCuttingUser = beltCuttingUser;
        this.card = card;
        this.count = count;
        this.complectationList = complectationList;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OffsetDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(OffsetDateTime created_at) {
        this.created_at = created_at;
    }

    public OffsetDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(OffsetDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public BeltCuttingUser getBeltCuttingUser() {
        return beltCuttingUser;
    }

    public void setBeltCuttingUser(BeltCuttingUser beltCuttingUser) {
        this.beltCuttingUser = beltCuttingUser;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Complectation> getComplectationList() {
        return complectationList;
    }

    public void setComplectationList(List<Complectation> complectationList) {
        this.complectationList = complectationList;
    }
}
