package com.yevheniimakar.beltcutting.model.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yevheniimakar.beltcutting.model.KnownAuthority;
import com.yevheniimakar.beltcutting.model.UserStatus;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;

import java.time.OffsetDateTime;
import java.util.EnumSet;
import java.util.Set;

public class UserResponse {
    Long id;
    String email;
    UserStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    OffsetDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Set<KnownAuthority> authorities;

    public UserResponse() {
    }

    public UserResponse(BeltCuttingUser user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.createdAt = user.getCreatedAt();
        this.authorities = EnumSet.copyOf(user.getAuthorities().keySet());
    }

    public UserResponse(Long id, String email, UserStatus status, OffsetDateTime createdAt, Set<KnownAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<KnownAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<KnownAuthority> authorities) {
        this.authorities = authorities;
    }
}
