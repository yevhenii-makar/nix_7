package com.yevheniimakar.beltcutting.model.equipment.response;

import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.card.response.CardResponseViewInList;

public class EquipmentTemplateResponse {
    private Integer size;
    private CardResponseViewInList card;

    public EquipmentTemplateResponse() {
    }

    public EquipmentTemplateResponse(Integer size, Card card) {
        this.size = size;
        this.card = new CardResponseViewInList(card);

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

}
