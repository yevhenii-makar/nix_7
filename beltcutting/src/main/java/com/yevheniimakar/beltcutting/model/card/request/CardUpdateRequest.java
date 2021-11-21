package com.yevheniimakar.beltcutting.model.card.request;

import javax.validation.constraints.NotBlank;

public class CardUpdateRequest {

    @NotBlank(message = "id mast be blank")
    private Long id;
    @NotBlank(message = "name id mast be blank")
    private String name;
    @NotBlank(message = "count id mast be blank")
    private Long count;
    private Long price;
    @NotBlank(message = "size mast be blank")
    private Integer size;
    @NotBlank(message = "manufacture id mast be blank")
    private Long manufacturerId;
    @NotBlank(message = "unit id mast be blank")
    private Long unitId;

    private long cardId;

    public CardUpdateRequest(Long id, String name, Long count, Long price, Integer size, Long manufacturerId, Long unitId, long cardId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.size = size;
        this.manufacturerId = manufacturerId;
        this.unitId = unitId;
        this.cardId = cardId;
    }

    public CardUpdateRequest() {
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

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }
}
