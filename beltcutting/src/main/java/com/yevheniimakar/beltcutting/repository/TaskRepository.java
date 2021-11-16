package com.yevheniimakar.beltcutting.repository;


import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;
import com.yevheniimakar.beltcutting.model.task.Task;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {


    @Query("select t from Task t  where t.status in :techStatuses or (t.status in :managerStatuses and t.beltCuttingUser = :beltCuttingUser )")
    Page<Task> findByTaskStatusListAndUser(BeltCuttingUser beltCuttingUser, List<TaskStatus> techStatuses, List<TaskStatus> managerStatuses, Pageable pageable);

}
