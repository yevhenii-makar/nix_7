package com.yevheniimakar.beltcutting.model.card.request;

import com.yevheniimakar.beltcutting.model.card.response.CardResponseViewInList;

public class CardUpdateRequest {

    private Long id;
    private String name;
    private Long count;
    private Long price;
    private Integer size;
    private String manufacturer;
    private String unit;
    private long cardId;

    public CardUpdateRequest(Long id, String name, Long count, Long price, Integer size, String manufacturer, String unit, long cardId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.size = size;
        this.manufacturer = manufacturer;
        this.unit = unit;
        this.cardId = cardId;
    }
    public CardUpdateRequest() {}

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }
}
