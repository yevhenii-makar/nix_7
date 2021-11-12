package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.complectation.Complectation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplectationRepository extends JpaRepository<Complectation, Long> {
}
