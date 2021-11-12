package com.yevheniimakar.beltcutting.model.complectation.response;


import com.yevheniimakar.beltcutting.model.card.response.CardResponseViewInList;
import com.yevheniimakar.beltcutting.model.complectation.Complectation;

import java.util.ArrayList;
import java.util.List;

public class ComplectationResponse {
    private Long id;
    private Integer size;
    private CardResponseViewInList card;
    private Integer pieceNumber;

    public ComplectationResponse() {
    }

    public ComplectationResponse(Complectation complectation) {
        this.id = complectation.getId();
        this.size = complectation.getSize();
        this.card = new CardResponseViewInList(complectation.getCard());
        this.pieceNumber = complectation.getPiece().getPiecesNumber();
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

    public CardResponseViewInList getCard() {
        return card;
    }

    public void setCard(CardResponseViewInList card) {
        this.card = card;
    }

    public Integer getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(Integer pieceNumber) {
        this.pieceNumber = pieceNumber;
    }
}
