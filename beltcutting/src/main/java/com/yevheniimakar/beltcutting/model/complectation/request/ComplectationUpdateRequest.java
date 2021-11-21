package com.yevheniimakar.beltcutting.model.complectation.request;

import javax.validation.constraints.NotBlank;

public class ComplectationUpdateRequest {

    @NotBlank(message = "id mast be blank")
    private Long id;
    @NotBlank(message = "size mast be blank")
    private Integer size;
    @NotBlank(message = "taskId mast be blank")
    private Long taskId;
    @NotBlank(message = "cardId mast be blank")
    private Long cardId;
    @NotBlank(message = "pieceId mast be blank")
    private Long pieceId;

    public ComplectationUpdateRequest() {
    }

    public ComplectationUpdateRequest(Long id, Integer size, Long taskId, Long cardId, Long pieceId) {
        this.id = id;
        this.size = size;
        this.taskId = taskId;
        this.cardId = cardId;
        this.pieceId = pieceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
