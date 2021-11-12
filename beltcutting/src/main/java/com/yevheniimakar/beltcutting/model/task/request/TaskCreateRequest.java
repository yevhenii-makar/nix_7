package com.yevheniimakar.beltcutting.model.task.request;

import com.yevheniimakar.beltcutting.model.task.TaskType;

public class TaskCreateRequest {

    private String name;
    private String message;
    private TaskType type;
    private long cardId;
    private int count;

    public TaskCreateRequest(String name, String message, TaskType type, long cardId, int count) {
        this.name = name;
        this.message = message;
        this.type = type;
        this.cardId = cardId;
        this.count = count;
    }
     public TaskCreateRequest() {}

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
