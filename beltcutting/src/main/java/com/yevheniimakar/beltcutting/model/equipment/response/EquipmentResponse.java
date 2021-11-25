package com.yevheniimakar.beltcutting.model.equipment.response;

import com.yevheniimakar.beltcutting.model.card.response.CardResponseViewInList;
import com.yevheniimakar.beltcutting.model.equipment.Equipment;

public class EquipmentResponse {
    private Long id;
    private Integer size;
    private CardResponseViewInList card;
    private Integer pieceNumber;

    public EquipmentResponse() {
    }

    public EquipmentResponse(Equipment equipment) {
        this.id = equipment.getId();
        this.size = equipment.getSize();
        this.card = new CardResponseViewInList(equipment.getCard());
        this.pieceNumber = equipment.getPiece().getPiecesNumber();
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
