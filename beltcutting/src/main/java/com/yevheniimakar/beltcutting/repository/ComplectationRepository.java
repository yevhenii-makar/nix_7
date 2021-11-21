package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.complectation.Complectation;
import com.yevheniimakar.beltcutting.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplectationRepository extends JpaRepository<Complectation, Long> {

    List<Complectation> findByTask(Task task);
}
