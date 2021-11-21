package com.yevheniimakar.beltcutting.model.auth.request;

import com.fasterxml.jackson.annotation.JsonAlias;

public class SignInRequest {

    @JsonAlias({"username", "email"})
    String login;
    String password;

    public SignInRequest() {

    }

    public SignInRequest(@JsonAlias({"username", "email"}) String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
