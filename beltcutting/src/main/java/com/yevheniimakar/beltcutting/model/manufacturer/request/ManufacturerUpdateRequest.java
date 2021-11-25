package com.yevheniimakar.beltcutting.model.manufacturer.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ManufacturerUpdateRequest {

    @NotNull(message = "id mast be not null")
    @Min(value = 1, message = "id value must be positive")
    private Long id;
    @NotBlank(message = "name mast be blank")
    private String name;

    public ManufacturerUpdateRequest() {
    }

    public ManufacturerUpdateRequest(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
