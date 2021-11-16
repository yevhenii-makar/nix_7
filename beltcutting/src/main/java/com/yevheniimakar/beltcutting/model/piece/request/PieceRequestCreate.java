package com.yevheniimakar.beltcutting.model.piece.request;

import javax.validation.constraints.Min;

public class PieceRequestCreate {

    @Min(value = 1, message = "The value must be more than zero")
    int size;
    Long unitId;

    public PieceRequestCreate() {
    }

    public PieceRequestCreate(@Min(value = 1, message = "The value must be more than zero")int size, Long unitId) {
        this.size = size;
        this.unitId = unitId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }
}
