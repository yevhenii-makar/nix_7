package com.yevheniimakar.beltcutting.model.card.response;

import com.yevheniimakar.beltcutting.model.card.Card;

public class CardResponseSingle {
    private Long id;
    private String name;
    private Integer count;
    private Long price;
    private Integer size;
    private String manufacturer;
    private String unit;
    private CardResponseViewInList accessory;

    public CardResponseSingle() {}

    public CardResponseSingle(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.count = card.getCount();
        this.price = card.getPrice();
        this.manufacturer = card.getManufacturer().getName();
        this.unit = card.getUnit().getName();
        this.size = card.getSize();
        this.accessory = new CardResponseViewInList(card.getAccessory());
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
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

    public CardResponseViewInList getAccessory() {
        return accessory;
    }

    public void setAccessory(CardResponseViewInList accessory) {
        this.accessory = accessory;
    }
}
