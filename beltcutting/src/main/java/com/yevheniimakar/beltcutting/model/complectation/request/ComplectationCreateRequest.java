package com.yevheniimakar.beltcutting.model.complectation.request;

import javax.validation.constraints.NotBlank;

public class ComplectationCreateRequest {
    @NotBlank(message = "ышяу mast be blank")
    private Integer size;
    @NotBlank(message = "taskId mast be blank")
    private Long taskId;
    @NotBlank(message = "cardId mast be blank")
    private Long cardId;
    private Long pieceId;

    public ComplectationCreateRequest() {
    }

    public ComplectationCreateRequest(Integer size, Long taskId, Long cardId, Long pieceId) {
        this.size = size;
        this.taskId = taskId;
        this.cardId = cardId;
        this.pieceId = pieceId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getPieceId() {
        return pieceId;
    }

    public void setPieceId(Long pieceId) {
        this.pieceId = pieceId;
    }
}
