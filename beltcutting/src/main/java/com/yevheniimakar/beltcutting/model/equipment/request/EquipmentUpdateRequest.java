package com.yevheniimakar.beltcutting.model.equipment.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EquipmentUpdateRequest {

    @NotNull(message = "id mast be not null")
    @Min(value = 1, message = "id value must be positive")
    private Long id;
    @NotNull(message = "size mast be not null")
    @Min(value = 1, message = "The value must be positive")
    private Integer size;
    @NotNull(message = "taskId mast be not null")
    @Min(value = 1, message = "The value must be positive")
    private Long taskId;
    @NotNull(message = "cardId mast be not null")
    @Min(value = 1, message = "The value must be positive")
    private Long cardId;
    @NotNull(message = "pieceId mast be not null")
    @Min(value = 1, message = "The value must be positive")
    private Long pieceId;

    public EquipmentUpdateRequest() {
    }

    public EquipmentUpdateRequest(Long id, Integer size, Long taskId, Long cardId, Long pieceId) {
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
