package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece, Long> {
}
