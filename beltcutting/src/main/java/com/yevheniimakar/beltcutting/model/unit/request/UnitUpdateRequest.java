package com.yevheniimakar.beltcutting.model.unit.request;

import javax.validation.constraints.NotBlank;

public class UnitUpdateRequest {

    @NotBlank(message = "Id mast be blank")
    private Long id;

    @NotBlank(message = "tname mast be blank")
    private String name;

    public UnitUpdateRequest() {
    }

    public UnitUpdateRequest(Long id, String name) {
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
