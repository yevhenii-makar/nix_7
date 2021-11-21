package com.yevheniimakar.beltcutting.model.manufacturer.request;

import javax.validation.constraints.NotBlank;

public class ManufacturerUpdateRequest {

    @NotBlank(message = "id mast be blank")
    Long id;
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
