package com.yevheniimakar.beltcutting.model.task.request;

import com.yevheniimakar.beltcutting.model.task.TaskType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TaskUpdateRequest {
    @NotNull(message = "id mast be blank")
    @Min(value = 1, message = "id value must be positive")
    private Long id;
    @NotBlank(message = "name mast be blank")
    private String name;
    @NotBlank(message = "massage mast be blank")
    private String message;
    @NotNull(message = "cardId mast be not null")
    @Min(value = 1, message = "count value must be positive")
    private Long cardId;
    @NotNull(message = "count mast be not null")
    @Min(value = 1, message = "count value must be positive")
    private int count;

    public TaskUpdateRequest(
            Long id,
            String name,
            String message,
            TaskType type,
            Long cardId,
            int count) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.cardId = cardId;
        this.count = count;

    }

    public TaskUpdateRequest() {
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

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
