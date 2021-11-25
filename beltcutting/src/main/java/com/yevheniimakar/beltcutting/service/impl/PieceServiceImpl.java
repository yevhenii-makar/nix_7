package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.piece.Piece;
import com.yevheniimakar.beltcutting.model.piece.request.PieceRequestCreate;
import com.yevheniimakar.beltcutting.model.piece.response.PieceResponseViewInList;
import com.yevheniimakar.beltcutting.model.unit.Unit;
import com.yevheniimakar.beltcutting.repository.PieceRepository;
import com.yevheniimakar.beltcutting.service.CardService;
import com.yevheniimakar.beltcutting.service.PieceService;
import com.yevheniimakar.beltcutting.service.UnitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PieceServiceImpl implements PieceService {

    private final PieceRepository pieceRepository;
    private final CardService cardService;
    private final UnitService unitService;

    public PieceServiceImpl(PieceRepository pieceRepository, CardService cardService, UnitService unitService) {
        this.pieceRepository = pieceRepository;
        this.cardService = cardService;
        this.unitService = unitService;
    }

    @Override
    public List<PieceResponseViewInList> getPieceListByCardId(Long id) {
        return pieceRepository.findByCardId(id).stream().map(PieceResponseViewInList::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PieceResponseViewInList> savePieceListCardId(List<PieceRequestCreate> piecesRequest, Long cardId) {
        Card card = cardService.getCardById(cardId);
        Set<Integer> pieceNumberSet = pieceRepository.getPiecesNumberSetByCard(card);
        List<Unit> units = unitService.getAll();
        List<Piece> pieces = new ArrayList<>(piecesRequest.size());

        for (PieceRequestCreate pc : piecesRequest) {
            Piece piece = new Piece();

            piece.setSize(pc.getSize());
            piece.setCard(card);

            piece.setUnit(units.stream().filter(u -> u.getId() == pc.getUnitId()).findFirst().get());

            int pieceNumber = 1;
            while (pieceNumberSet.contains(pieceNumber)) {
                pieceNumber++;
            }
            pieceNumberSet.add(pieceNumber);
            piece.setPiecesNumber(pieceNumber);

            pieces.add(piece);

        }
        return pieceRepository.saveAll(pieces).stream().map(PieceResponseViewInList::new).collect(Collectors.toList());
    }
}
