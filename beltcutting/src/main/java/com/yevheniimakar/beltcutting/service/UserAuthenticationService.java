package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserAuthenticationService {


    boolean isAdmin(Authentication authentication);

    boolean isTechnicalSpecialist(Authentication authentication);

    boolean isManager(Authentication authentication);

    boolean isMachineOperator(Authentication authentication);

    List<TaskStatus> getTechStatuses(Authentication authentication);

    List<TaskStatus> getManagerStatuses();

    List<TaskStatus> getAdminStatuses();

    List<List> getStatuses(Authentication authentication);
}
