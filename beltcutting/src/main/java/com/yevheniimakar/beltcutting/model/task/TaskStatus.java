package com.yevheniimakar.beltcutting.model.task;

import com.yevheniimakar.beltcutting.model.task.Task;

import javax.persistence.*;
import java.util.List;


public enum  TaskStatus {
    CREATED,
    TECHNICAL_REVIEW,
    PRODUCTION_REVIEW,
    READY,
    END,
    CLARIFICATION_REQUEST,
    CANCELED

}
