package com.yevheniimakar.beltcutting.model.task.request;

import com.yevheniimakar.beltcutting.model.complectation.request.ComplectationUpdateRequest;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import com.yevheniimakar.beltcutting.model.task.TaskType;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class TaskUpdateRequest {
    List<ComplectationUpdateRequest> complectationRequestList;
    @NotBlank(message = "idd mast be blank")
    private long id;
    @NotBlank(message = "name mast be blank")
    private String name;
    @NotBlank(message = "massage mast be blank")
    private String message;
    @NotBlank(message = "userId mast be blank")
    private long userId;
    private TaskStatus status;
    @NotBlank(message = "caddId mast be blank")
    private long cardId;
    @NotBlank(message = "count mast be blank")
    private int count;

    public TaskUpdateRequest(
            long id,
            String name,
            String message,
            long userId,
            TaskType type,
            long cardId,
            int count,
            List<ComplectationUpdateRequest> complectationRequestList, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.userId = userId;
        this.cardId = cardId;
        this.count = count;
        this.complectationRequestList = complectationRequestList;
        this.status = status;
    }

    public TaskUpdateRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public List<ComplectationUpdateRequest> getComplectationRequestList() {
        return complectationRequestList;
    }

    public void setComplectationRequestList(List<ComplectationUpdateRequest> complectationRequestList) {
        this.complectationRequestList = complectationRequestList;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
