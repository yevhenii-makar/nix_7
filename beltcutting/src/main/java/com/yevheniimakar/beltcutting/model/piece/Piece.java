package com.yevheniimakar.beltcutting.model.piece;

import com.yevheniimakar.beltcutting.model.unit.Unit;
import com.yevheniimakar.beltcutting.model.card.Card;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pieces")
public class Piece {

    @Id
    private Long id;
    private Integer size;
    private Integer piecesNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(id, piece.id) &&
                Objects.equals(size, piece.size) &&
                Objects.equals(piecesNumber, piece.piecesNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, piecesNumber);
    }
}
