package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.piece.Piece;
import com.yevheniimakar.beltcutting.model.piece.response.PieceResponseViewInList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PieceRepository extends JpaRepository<Piece, Long> {


    List<Piece> findByIdIn(List<Long> id);

    @Query("select p from Piece p  join Card c where c.id = :id and p.size <> 0 order by p.piecesNumber")
    List<Piece> findByCardId(Long id);

    @Query("select p.piecesNumber from Piece p join Card c where c = :card order by p.piecesNumber")
    Set<Integer> getPiecesNumberSetByCard(Card card);
}
