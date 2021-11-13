package com.yevheniimakar.beltcutting.model.auth.response;

public class AccessTokenResponse{

    String accessToken;
    String refreshToken;
    long expireIn;

    public AccessTokenResponse() {
    }

    public AccessTokenResponse(String accessToken, String refreshToken, long expireIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireIn = expireIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(long expireIn) {
        this.expireIn = expireIn;
    }
}
