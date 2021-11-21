package com.yevheniimakar.beltcutting.model.manufacturer.request;

public class ManufacturerUpdateRequest {

    Long id;

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
        name = name;
    }
}
