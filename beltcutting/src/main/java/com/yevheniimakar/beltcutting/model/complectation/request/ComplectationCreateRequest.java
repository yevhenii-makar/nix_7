package com.yevheniimakar.beltcutting.model.complectation.request;

public class ComplectationCreateRequest {
    private Integer size;
    private Long taskId;
    private Long cardId;
    private Long pieceId;

    public ComplectationCreateRequest() {}

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
