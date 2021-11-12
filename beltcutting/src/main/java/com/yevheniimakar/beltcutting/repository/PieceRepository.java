package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.piece.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PieceRepository extends JpaRepository<Piece, Long> {


    List<Piece> findByIdIn(List<Long> id);

}
