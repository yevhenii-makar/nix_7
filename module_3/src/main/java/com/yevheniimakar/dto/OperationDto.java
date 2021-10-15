package com.yevheniimakar.dto;


import java.time.Instant;

public class OperationDto {


    private Long id;

    private Long categoryDtoId;

    private Long value;

    private Long accountDtoId;

    private Instant date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryDtoId() {
        return categoryDtoId;
    }

    public void setCategoryDtoId(Long categoryDtoId) {
        this.categoryDtoId = categoryDtoId;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getAccountDtoId() {
        return accountDtoId;
    }

    public void setAccountDtoId(Long accountDtoId) {
        this.accountDtoId = accountDtoId;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
