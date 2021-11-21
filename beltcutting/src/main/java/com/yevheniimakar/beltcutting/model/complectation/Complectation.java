package com.yevheniimakar.beltcutting.model.complectation;

import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.piece.Piece;
import com.yevheniimakar.beltcutting.model.task.Task;

import javax.persistence.*;

@Entity
@Table(name = "complectations")
public class Complectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer size;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "piece_id")
    private Piece piece;

    public Complectation(Long id, Integer size, Task task, Card card, Piece piece) {
        this.id = id;
        this.size = size;
        this.task = task;
        this.card = card;
        this.piece = piece;
    }

    public Complectation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

}
