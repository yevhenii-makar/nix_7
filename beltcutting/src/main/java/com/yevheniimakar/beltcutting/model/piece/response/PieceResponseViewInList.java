package com.yevheniimakar.beltcutting.model.piece.response;

import com.yevheniimakar.beltcutting.model.piece.Piece;
import com.yevheniimakar.beltcutting.model.unit.response.UnitResponse;

public class PieceResponseViewInList {

    private Long id;
    private Integer size;
    private Integer piecesNumber;
    private UnitResponse unit;

    public PieceResponseViewInList() {}

    public PieceResponseViewInList(Piece piece) {
        this.id = piece.getId();
        this.size = piece.getSize();
        this.piecesNumber = piece.getPiecesNumber();
        this.unit = new UnitResponse(piece.getUnit());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPiecesNumber() {
        return piecesNumber;
    }

    public void setPiecesNumber(Integer piecesNumber) {
        this.piecesNumber = piecesNumber;
    }

    public UnitResponse getUnit() {
        return unit;
    }

    public void setUnit(UnitResponse unit) {
        this.unit = unit;
    }
}
