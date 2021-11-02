package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
