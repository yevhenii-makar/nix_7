package com.yevheniimakar.beltcutting.model.piece.request;

import java.util.List;

public class PieceCreateList {

    private List<PieceRequestCreate> pieceRequestCreateList;

    public PieceCreateList() {
    }

    public PieceCreateList(List<PieceRequestCreate> pieceRequestCreateList) {
        this.pieceRequestCreateList = pieceRequestCreateList;
    }

    public List<PieceRequestCreate> getPieceRequestCreateList() {
        return pieceRequestCreateList;
    }

    public void setPieceRequestCreateList(List<PieceRequestCreate> pieceRequestCreateList) {
        this.pieceRequestCreateList = pieceRequestCreateList;
    }
}
