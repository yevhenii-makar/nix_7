package com.yevheniimakar.beltcutting.model.user.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveUserRequest {

    @Email(message = "email must be a valid email string")
    @NotNull(message = "email must not be null")
    String email;

    @NotBlank(message = "password must not be blank")
    @Size(min = 8, message = "password's length must be at least 8")
    String password;

    public SaveUserRequest() {}

    public SaveUserRequest(@Email(message = "email must be a valid email string")
                           @NotNull(message = "email must not be null") String email,
                           @NotBlank(message = "password must not be blank") @Size(min = 8, message = "password's length must be at least 8") String password) {
        this.email = email;
        this.password = password;
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
}
