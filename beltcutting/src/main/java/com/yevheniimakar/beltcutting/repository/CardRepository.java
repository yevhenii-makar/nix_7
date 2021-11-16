package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.card.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByIdIn(List<Long> id);

    Page<Card> findAll(Pageable pageble);
}
