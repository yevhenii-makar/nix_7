package com.yevheniimakar.beltcutting.controller.task;

import com.yevheniimakar.beltcutting.Routes;
import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import com.yevheniimakar.beltcutting.model.task.request.TaskCreateRequest;
import com.yevheniimakar.beltcutting.model.task.request.TaskUpdateRequest;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseSingle;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseViewInList;
import com.yevheniimakar.beltcutting.service.TaskService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.TASKS)
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseSingle createTask(@RequestBody @Valid TaskCreateRequest request, Authentication authentication) {

        return taskService.create(request, authentication);
    }

    @GetMapping
    @PageableAsQueryParam
    public Page<TaskResponseViewInList> getCurrentTask(
            Authentication authentication,
            @Parameter(hidden = true) Pageable pageable) {
        return taskService.getUserTaskList(pageable, authentication);
    }

    @GetMapping("/{id}")
    public TaskResponseSingle getTask(@PathVariable Long id, Authentication authentication) {
        return taskService.findById(id, authentication);
    }

    @PatchMapping("/{id}")
    public TaskResponseSingle updateTask(@PathVariable Long id, @RequestBody @Valid TaskUpdateRequest request, Authentication authentication) {
        return taskService.update(id, request, authentication);
    }

    @PatchMapping("/{id}/status")
    public TaskResponseSingle changeTaskStatus(@PathVariable Long id, TaskStatus status, Authentication authentication) {
        return taskService.changeTaskStatus(id, status, authentication);
    }

}
