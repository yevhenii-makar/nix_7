package com.yevheniimakar.beltcutting.model;

import org.springframework.security.core.GrantedAuthority;

public enum KnownAuthority implements GrantedAuthority {

    ROLE_MANAGER,

    ROLE_TECHNICAL_SPECIALIST,

    ROLE_MACHINE_OPERATOR,

    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
