package com.yevheniimakar.beltcutting.model.task.request;

import com.yevheniimakar.beltcutting.model.task.TaskType;

import javax.validation.constraints.NotBlank;

public class TaskCreateRequest {

    @NotBlank(message = "name mast be blank")
    private String name;

    @NotBlank(message = "message mast be blank")
    private String message;
    @NotBlank(message = "type mast be blank")
    private TaskType type;
    @NotBlank(message = "cardId mast be blank")
    private long cardId;
    @NotBlank(message = "count mast be blank")
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
