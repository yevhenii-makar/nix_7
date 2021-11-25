package com.yevheniimakar.beltcutting.model.card.response;

import com.yevheniimakar.beltcutting.model.card.Card;

public class CardResponseViewInList {

    private Long id;
    private String name;
    private Integer count;
    private Long price;
    private String manufacturer;
    private String unit;

    public CardResponseViewInList() {

    }

    public CardResponseViewInList(Long id, String name, Integer count, Long price, String manufacturer, String unit) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.manufacturer = manufacturer;
        this.unit = unit;
    }

    public CardResponseViewInList(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.count = card.getCount();
        this.price = card.getPrice();
        this.manufacturer = card.getManufacturer().getName();
        this.unit = card.getUnit().getName();
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
