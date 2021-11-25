package com.yevheniimakar.beltcutting.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yevheniimakar.beltcutting.Routes;
import com.yevheniimakar.beltcutting.config.security.filters.JWTAuthenticationFilter;
import com.yevheniimakar.beltcutting.config.security.filters.JWTAuthorizationFilter;
import com.yevheniimakar.beltcutting.config.security.properties.BeltCuttingSecurityProperties;
import com.yevheniimakar.beltcutting.model.KnownAuthority;
import com.yevheniimakar.beltcutting.model.user.request.SaveUserRequest;
import com.yevheniimakar.beltcutting.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.PostConstruct;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(BeltCuttingSecurityProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    private final BeltCuttingSecurityProperties securityProperties;

    private final UserServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    private final ObjectMapper objectMapper;

    public SecurityConfig(
            BeltCuttingSecurityProperties securityProperties,
            UserServiceImpl userService,
            PasswordEncoder passwordEncoder,
            ObjectMapper objectMapper
    ) {
        this.securityProperties = securityProperties;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        setupDefaultAdmins();
    }

    private void setupDefaultAdmins() {
        List<SaveUserRequest> requests = securityProperties.getAdmins().entrySet().stream()
                .map(entry -> new SaveUserRequest(
                        entry.getValue().getEmail(),
                        new String(entry.getValue().getPassword()), EnumSet.of(KnownAuthority.ROLE_ADMIN), entry.getValue().getName()))
                .peek(admin -> log.info("Default admin found: {} <{}>", admin.getEmail()))
                .collect(Collectors.toList());
        userService.mergeAdmins(requests);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.POST, Routes.USERS, Routes.TOKEN + "/refresh").permitAll()
                .antMatchers(HttpMethod.POST, Routes.USERS + "/admins").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, Routes.USERS).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, Routes.CARDS + "/{id:\\d+}").hasAnyRole("ADMIN", "TECHNICAL_SPECIALIST")
                .antMatchers(HttpMethod.POST, Routes.PIECES + "/{id:\\d+}", Routes.PIECES + "/**/{id:\\d+}").hasAnyRole("ADMIN", "TECHNICAL_SPECIALIST")
                .antMatchers(HttpMethod.POST, Routes.TASKS).hasAnyRole("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.POST, Routes.MANUFACTURERS + "/**", Routes.MANUFACTURERS + "{id:\\d+}/").hasAnyRole("ADMIN", "TECHNICAL_SPECIALIST")
                .antMatchers(HttpMethod.PATCH, Routes.MANUFACTURERS, Routes.MANUFACTURERS + "{id:\\d+}/").hasAnyRole("ADMIN", "TECHNICAL_SPECIALIST")
                .antMatchers(HttpMethod.POST, Routes.UNITS + "/**", Routes.UNITS + "{id:\\d+}/").hasAnyRole("ADMIN", "TECHNICAL_SPECIALIST")
                .antMatchers(HttpMethod.PATCH, Routes.UNITS, Routes.UNITS + "{id:\\d+}/").hasAnyRole("ADMIN", "TECHNICAL_SPECIALIST")
                .antMatchers(HttpMethod.POST, Routes.ADMIN + "/**").hasRole("ADMIN")
                .antMatchers(Routes.ADMIN + "/{id:\\d+}/**", Routes.ADMIN + "/**/{id:\\d+}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(jwtAuthenticationFilter())
                .addFilter(jwtAuthorizationFilter())
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager(), objectMapper);
        filter.setFilterProcessesUrl(Routes.TOKEN);
        return filter;
    }

    private JWTAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JWTAuthorizationFilter(authenticationManager(), securityProperties.getJwt());
    }

    private CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}

