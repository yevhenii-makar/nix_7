package com.yevheniimakar.beltcutting.model.task.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yevheniimakar.beltcutting.model.card.response.CardResponseViewInList;
import com.yevheniimakar.beltcutting.model.complectation.response.ComplectationResponse;
import com.yevheniimakar.beltcutting.model.task.Task;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TaskResponseSingle {
    private Long id;
    private String name;
    private String message;
    private int count;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime updated_at;
    private TaskStatus status;
    private CardResponseViewInList card;
    private List<ComplectationResponse> complectationResponses;

    public TaskResponseSingle(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.message = task.getMessage();
        this.count = task.getCount();
        this.updated_at = task.getUpdated_at();
        this.status = task.getStatus();
        this.card = new CardResponseViewInList(task.getCard());
        this.complectationResponses = task.getComplectationList().stream().map(ComplectationResponse::new).collect(Collectors.toList());

    }

    public TaskResponseSingle() {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public CardResponseViewInList getCard() {
        return card;
    }

    public void setCard(CardResponseViewInList card) {
        this.card = card;
    }

    public List<ComplectationResponse> getComplectationResponses() {
        return complectationResponses;
    }

    public void setComplectationResponses(List<ComplectationResponse> complectationResponses) {
        this.complectationResponses = complectationResponses;
    }
}
