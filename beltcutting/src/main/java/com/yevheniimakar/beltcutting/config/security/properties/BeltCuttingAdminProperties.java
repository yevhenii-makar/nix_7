package com.yevheniimakar.beltcutting.config.security.properties;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BeltCuttingAdminProperties {

    @Email(message = "email must be a valid email string")
    @NotNull(message = "email must not be null")
    private String email;

    @NotEmpty(message = "password must not be empty")
    @Size(min = 8, message = "password's length must be at least 8")
    private char[] password;

    @NotNull(message = "name must not be null")
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
