package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.model.KnownAuthority;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import com.yevheniimakar.beltcutting.service.UserAuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Override
    public boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().contains(KnownAuthority.ROLE_ADMIN);
    }

    @Override
    public boolean isTechnicalSpecialist(Authentication authentication) {
        return authentication.getAuthorities().contains(KnownAuthority.ROLE_TECHNICAL_SPECIALIST);
    }

    @Override
    public boolean isManager(Authentication authentication) {
        return authentication.getAuthorities().contains(KnownAuthority.ROLE_MANAGER);
    }

    @Override
    public boolean isMachineOperator(Authentication authentication) {
        return authentication.getAuthorities().contains(KnownAuthority.ROLE_MACHINE_OPERATOR);
    }

    @Override
    public List<TaskStatus> getTechStatuses(Authentication authentication) {
        List<TaskStatus> statuses = new ArrayList<>(2);

        if (isTechnicalSpecialist(authentication)) {
            statuses.add(TaskStatus.TECHNICAL_REVIEW);
        }
        if (isMachineOperator(authentication)) {
            statuses.add(TaskStatus.PRODUCTION_REVIEW);
        }

        return statuses;

    }

    @Override
    public List<TaskStatus> getManagerStatuses() {
        List<TaskStatus> statuses = new ArrayList<>(4);
        statuses.add(TaskStatus.CREATED);
        statuses.add(TaskStatus.READY);
        statuses.add(TaskStatus.CLARIFICATION_REQUEST);
        statuses.add(TaskStatus.CANCELED);
        return statuses;

    }

    @Override
    public List<TaskStatus> getAdminStatuses() {
        List<TaskStatus> statuses = new ArrayList<>(6);
        statuses.add(TaskStatus.TECHNICAL_REVIEW);
        statuses.add(TaskStatus.PRODUCTION_REVIEW);
        statuses.add(TaskStatus.CREATED);
        statuses.add(TaskStatus.READY);
        statuses.add(TaskStatus.CLARIFICATION_REQUEST);
        statuses.add(TaskStatus.CANCELED);
        return statuses;
    }

    @Override
    public List<List> getStatuses(Authentication authentication) {
        List<TaskStatus> techStatuses = null;
        List<TaskStatus> managerStatuses = null;
        List<List> statuses = new ArrayList<>(2);

        if (isAdmin(authentication)) {
            techStatuses = getAdminStatuses();
        } else {
            if (isManager(authentication)) {
                managerStatuses = getManagerStatuses();
            }
            if (isTechnicalSpecialist(authentication) || isMachineOperator(authentication)) {
                techStatuses = getTechStatuses(authentication);
            }
        }
        statuses.add(techStatuses);
        statuses.add(managerStatuses);
        return statuses;
    }
}
