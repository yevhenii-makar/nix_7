package com.yevheniimakar.beltcutting.model.unit.response;

import com.yevheniimakar.beltcutting.model.unit.Unit;

public class UnitResponse {

    private Long id;
    private String name;

    public UnitResponse() {
    }

    public UnitResponse(Unit unit) {
        this.id = unit.getId();
        this.name = unit.getName();
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
