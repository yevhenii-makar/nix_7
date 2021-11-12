package com.yevheniimakar.beltcutting.model.card.request;

import com.yevheniimakar.beltcutting.model.card.response.CardResponseViewInList;

public class CardCreateRequest {
    private String name;
    private String manufacturer;

    public CardCreateRequest() {}

    public CardCreateRequest(String name, String manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
