package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import com.yevheniimakar.beltcutting.model.task.request.TaskCreateRequest;
import com.yevheniimakar.beltcutting.model.task.request.TaskUpdateRequest;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseSingle;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseViewInList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface TaskService {

    Page<TaskResponseViewInList> list(Pageable pageable);

    Page<TaskResponseViewInList> getUserTaskList(Pageable pageable, Authentication authentication);

    TaskResponseSingle update(Long id, TaskUpdateRequest request, Authentication authentication);

    TaskResponseSingle create(TaskCreateRequest request, Authentication authentication);

    TaskResponseSingle findById(Long id, Authentication authentication);

    TaskResponseSingle changeTaskStatus(Long id, TaskStatus status, Authentication authentication);
}
