package com.yevheniimakar.beltcutting.model;

import javax.persistence.*;

@Entity
@Table(name = "pieces")
public class Piece {

    @Id
    private Long id;
    private Integer size;
    private Integer piecesNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "units_id")
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cards_id")
    private Card card;

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


    public Integer getPiecesNumber() {
        return piecesNumber;
    }

    public void setPiecesNumber(Integer piecesNumber) {
        this.piecesNumber = piecesNumber;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

}
