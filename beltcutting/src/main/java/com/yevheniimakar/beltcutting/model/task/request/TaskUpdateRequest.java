package com.yevheniimakar.beltcutting.model.task.request;

import com.yevheniimakar.beltcutting.model.complectation.request.ComplectationUpdateRequest;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import com.yevheniimakar.beltcutting.model.task.TaskType;

import java.time.OffsetDateTime;
import java.util.List;

public class TaskUpdateRequest {
    private long id;
    private String name;
    private String message;
    private long userId;
    private TaskStatus status;
    private long cardId;
    private int count;
    private OffsetDateTime created_at;
    List<ComplectationUpdateRequest> complectationRequestList;



    public TaskUpdateRequest(
            long id,
            String name,
            String message,
            long userId,
            TaskType type,
            long cardId,
            int count,
            OffsetDateTime created_at,
            OffsetDateTime updated_at,
            List<ComplectationUpdateRequest> complectationRequestList, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.userId = userId;
        this.cardId = cardId;
        this.count = count;
        this.created_at = created_at;
        this.complectationRequestList = complectationRequestList;
        this.status = status;
    }

    public TaskUpdateRequest() {}

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public OffsetDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(OffsetDateTime created_at) {
        this.created_at = created_at;
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
