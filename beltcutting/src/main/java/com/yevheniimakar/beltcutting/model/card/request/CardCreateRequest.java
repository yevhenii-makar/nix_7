package com.yevheniimakar.beltcutting.model.card.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CardCreateRequest {
    @NotBlank(message = "name must be blank")
    private String name;

    @NotNull(message = "manufacture id must be not null")
    @Min(value = 1, message = "The value must be positive")
    private Long manufactureId;
    private Long accessoryId;
    private Integer size;

    @NotNull(message = "unit id must be not null")
    @Min(value = 1, message = "The value must be positive")
    private Long unitId;

    public CardCreateRequest() {
    }

    public CardCreateRequest(String name, String manufacturer, Long manufactureId, Long accessoryId, Integer size, Long unitId) {
        this.name = name;
        this.manufactureId = manufactureId;
        this.accessoryId = accessoryId;
        this.size = size;
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(Long manufactureId) {
        this.manufactureId = manufactureId;
    }

    public Long getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Long accessoryId) {
        this.accessoryId = accessoryId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }
}
