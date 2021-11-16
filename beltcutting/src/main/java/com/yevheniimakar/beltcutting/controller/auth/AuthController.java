package com.yevheniimakar.beltcutting.controller.auth;

import com.yevheniimakar.beltcutting.Routes;
import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.exceptions.auth.InvalidRefreshTokenException;
import com.yevheniimakar.beltcutting.model.auth.BeltCuttingUserDetails;
import com.yevheniimakar.beltcutting.model.auth.request.RefreshTokenRequest;
import com.yevheniimakar.beltcutting.model.auth.request.SignInRequest;
import com.yevheniimakar.beltcutting.model.auth.response.AccessTokenResponse;
import com.yevheniimakar.beltcutting.service.AuthService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.TOKEN)
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = SignInRequest.class)))
    public AccessTokenResponse login(@AuthenticationPrincipal BeltCuttingUserDetails userDetails) {
        return authService.getToken(userDetails);
    }

    @PostMapping(
            value = "/refresh",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public AccessTokenResponse refresh(@RequestBody @Valid RefreshTokenRequest request) {
        try {
            return authService.refreshToken(request.getRefreshToken());
        } catch (InvalidRefreshTokenException e) {
            throw BeltCuttingExceptions.invalidRefreshToken(e);
        }
    }

    @PostMapping(value = "/invalidate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void invalidate(@RequestBody @Valid RefreshTokenRequest request, @AuthenticationPrincipal String email) {
        try {
            authService.invalidateToken(request.getRefreshToken(), email);
        } catch (InvalidRefreshTokenException e) {
            throw BeltCuttingExceptions.invalidRefreshToken(e);
        }
    }

}
