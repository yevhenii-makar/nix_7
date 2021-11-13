package com.yevheniimakar.beltcutting.config.security.properties;

import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.time.Duration;

@Component
public class BeltCuttingJWTProperties {

    @NotEmpty
    private char[] secret;

    @DurationMax(minutes = 30)
    @DurationMin(minutes = 1)
    private Duration accessExpireIn;

    @DurationMax(days = 7)
    @DurationMin(hours = 12)
    private Duration refreshExpireIn;

    public char[] getSecret() {
        return secret;
    }

    public void setSecret(char[] secret) {
        this.secret = secret;
    }

    public Duration getAccessExpireIn() {
        return accessExpireIn;
    }

    public void setAccessExpireIn(Duration accessExpireIn) {
        this.accessExpireIn = accessExpireIn;
    }

    public Duration getRefreshExpireIn() {
        return refreshExpireIn;
    }

    public void setRefreshExpireIn(Duration refreshExpireIn) {
        this.refreshExpireIn = refreshExpireIn;
    }

}
