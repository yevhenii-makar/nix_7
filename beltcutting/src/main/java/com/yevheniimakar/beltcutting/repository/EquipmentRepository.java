package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.equipment.Equipment;
import com.yevheniimakar.beltcutting.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    List<Equipment> findByTask(Task task);
}
