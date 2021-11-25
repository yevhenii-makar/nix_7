package com.yevheniimakar.beltcutting.model.equipment.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EquipmentCreateRequest {
    @NotNull(message = "size mast be not null")
    @Min(value = 1, message = "The value must be positive")
    private Integer size;
    @NotNull(message = "taskId mast be not null")
    @Min(value = 1, message = "The value must be positive")
    private Long taskId;
    @NotNull(message = "cardId mast be not null")
    @Min(value = 1, message = "The value must be positive")
    private Long cardId;
    private Long pieceId;

    public EquipmentCreateRequest() {
    }

    public EquipmentCreateRequest(Integer size, Long taskId, Long cardId, Long pieceId) {
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
