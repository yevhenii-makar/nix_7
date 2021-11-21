package com.yevheniimakar.beltcutting.model.task.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yevheniimakar.beltcutting.model.task.Task;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;

import java.time.OffsetDateTime;
import java.util.Objects;

public class TaskResponseViewInList {

    private Long id;
    private String name;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime updated_at;
    private TaskStatus status;

    public TaskResponseViewInList(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.message = task.getMessage();
        this.updated_at = task.getUpdated_at();
        this.status = task.getStatus();
    }

    public TaskResponseViewInList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OffsetDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(OffsetDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskResponseViewInList that = (TaskResponseViewInList) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(message, that.message) &&
                Objects.equals(updated_at, that.updated_at) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, message, updated_at, status);
    }
}
