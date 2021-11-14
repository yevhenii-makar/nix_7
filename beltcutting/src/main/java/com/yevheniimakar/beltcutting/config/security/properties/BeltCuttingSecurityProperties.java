package com.yevheniimakar.beltcutting.config.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Validated
@ConfigurationProperties(prefix = "belt-cutting.security")
public class BeltCuttingSecurityProperties {

    @Valid
    @NestedConfigurationProperty
    private BeltCuttingJWTProperties jwt;

    private Map<@NotBlank String, @Valid BeltCuttingAdminProperties> admins;

    public BeltCuttingJWTProperties getJwt() {
        return jwt;
    }

    public void setJwt(BeltCuttingJWTProperties jwt) {
        this.jwt = jwt;
    }

    public Map<String, BeltCuttingAdminProperties> getAdmins() {
        return admins;
    }

    public void setAdmins(Map<String, BeltCuttingAdminProperties> admins) {
        this.admins = admins;
    }
}
