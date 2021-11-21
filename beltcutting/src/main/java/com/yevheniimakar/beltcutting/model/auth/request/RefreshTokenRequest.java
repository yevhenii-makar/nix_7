package com.yevheniimakar.beltcutting.model.auth.request;

import javax.validation.constraints.NotNull;

public class RefreshTokenRequest {

    @NotNull
    String refreshToken;

    public RefreshTokenRequest() {
    }

    public RefreshTokenRequest(@NotNull String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
