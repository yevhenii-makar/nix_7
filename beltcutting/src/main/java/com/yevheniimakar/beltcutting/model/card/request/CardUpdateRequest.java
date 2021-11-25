package com.yevheniimakar.beltcutting.model.card.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CardUpdateRequest {

    @NotNull(message = "id must be not null")
    @Min(value = 1, message = "The value must be positive")
    private Long id;
    @NotBlank(message = "name id mast be blank")
    private String name;
    @NotNull(message = "count must be not null")
    @Min(value = 1, message = "The value must be positive")
    private Long count;
    private Long price;
    @NotNull(message = "size must be not null")
    @Min(value = 1, message = "The value must be positive")
    private Integer size;
    @NotNull(message = "manufacture id must be not null")
    @Min(value = 1, message = "The value must be positive")
    private Long manufacturerId;
    @NotNull(message = "unit id must be not null")
    @Min(value = 1, message = "The value must be positive")
    private Long unitId;

    private Long accessoryId;

    public CardUpdateRequest(Long id, String name, Long count, Long price, Integer size, Long manufacturerId, Long unitId, long accessoryId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.size = size;
        this.manufacturerId = manufacturerId;
        this.unitId = unitId;
        this.accessoryId = accessoryId;
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

    public Long getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Long accessoryId) {
        this.accessoryId = accessoryId;
    }
}
