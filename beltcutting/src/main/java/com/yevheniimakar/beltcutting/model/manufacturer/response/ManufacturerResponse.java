package com.yevheniimakar.beltcutting.model.manufacturer.response;

import com.yevheniimakar.beltcutting.model.manufacturer.Manufacturer;

public class ManufacturerResponse {

    Long id;

    private String name;

    public ManufacturerResponse() {
    }

    public ManufacturerResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ManufacturerResponse(Manufacturer manufacturer) {
        this.id = manufacturer.getId();
        this.name = manufacturer.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

}
