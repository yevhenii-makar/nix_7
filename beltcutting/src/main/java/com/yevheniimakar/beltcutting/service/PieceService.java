package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.piece.request.PieceRequestCreate;
import com.yevheniimakar.beltcutting.model.piece.response.PieceResponseViewInList;

import java.util.List;

public interface PieceService {

    List<PieceResponseViewInList> getPieceListByCardId(Long id);

    List <PieceResponseViewInList> savePieceListByCardId(List<PieceRequestCreate> pices, Long cardId);
}
