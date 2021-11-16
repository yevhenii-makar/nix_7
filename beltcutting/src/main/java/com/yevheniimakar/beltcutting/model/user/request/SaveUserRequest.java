package com.yevheniimakar.beltcutting.model.user.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yevheniimakar.beltcutting.model.KnownAuthority;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class SaveUserRequest {

    @Email(message = "email must be a valid email string")
    @NotNull(message = "email must not be null")
    private String email;

    @NotBlank(message = "password must not be blank")
    @Size(min = 8, message = "password's length must be at least 8")
    private String password;

    @NotBlank(message = "name must not be blank")
    private String name;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    Set<KnownAuthority> authorities;


    public SaveUserRequest() {}

    public SaveUserRequest(@Email(message = "email must be a valid email string")
                           @NotNull(message = "email must not be null") String email,
                           @NotBlank(message = "password must not be blank") @Size(min = 8, message = "password's length must be at least 8") String password,
                           @NotBlank(message = "authorities must not be blank")@JsonInclude(JsonInclude.Include.NON_NULL) Set<KnownAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public SaveUserRequest(String email, String s, String key) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<KnownAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<KnownAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
