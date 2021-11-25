package com.yevheniimakar.beltcutting.model.task.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yevheniimakar.beltcutting.model.task.TaskType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TaskCreateRequest {

    @NotBlank(message = "name mast be blank")
    private String name;

    @NotBlank(message = "message mast be blank")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TaskType type;
    @NotNull(message = "cardId mast be not null")
    @Min(value = 1, message = "id value must be positive")
    private long cardId;
    @NotNull(message = "count mast be not null")
    @Min(value = 1, message = "id value must be positive")
    private int count;

    public TaskCreateRequest(String name, String message, TaskType type, long cardId, int count) {
        this.name = name;
        this.message = message;
        this.type = type;
        this.cardId = cardId;
        this.count = count;
    }

    public TaskCreateRequest() {
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

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
