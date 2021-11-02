package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
