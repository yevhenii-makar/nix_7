package com.yevheniimakar.beltcutting.model.unit.response;

import com.yevheniimakar.beltcutting.model.unit.Unit;

public class UnitResponse {

    private int id;
    private String name;

    public UnitResponse() {}

   public UnitResponse(Unit unit) {
        this.id = unit.getId();
        this.name = unit.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
