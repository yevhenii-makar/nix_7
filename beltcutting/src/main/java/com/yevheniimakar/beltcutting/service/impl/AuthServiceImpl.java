package com.yevheniimakar.beltcutting.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yevheniimakar.beltcutting.config.security.SecurityConstants;
import com.yevheniimakar.beltcutting.config.security.properties.BeltCuttingJWTProperties;
import com.yevheniimakar.beltcutting.config.security.properties.BeltCuttingSecurityProperties;
import com.yevheniimakar.beltcutting.exceptions.auth.InvalidRefreshTokenException;
import com.yevheniimakar.beltcutting.model.UserStatus;
import com.yevheniimakar.beltcutting.model.auth.BeltCuttingUserDetails;
import com.yevheniimakar.beltcutting.model.auth.RefreshToken;
import com.yevheniimakar.beltcutting.model.auth.response.AccessTokenResponse;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;
import com.yevheniimakar.beltcutting.repository.RefreshTokenRepository;
import com.yevheniimakar.beltcutting.repository.UserRepository;
import com.yevheniimakar.beltcutting.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    private final Duration jwtExpiration;

    private final Duration refreshExpiration;

    private final Algorithm algorithm;

    public AuthServiceImpl(BeltCuttingSecurityProperties securityProperties,
                           RefreshTokenRepository refreshTokenRepository,
                           UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        BeltCuttingJWTProperties jwtProperties = securityProperties.getJwt();
        this.jwtExpiration = jwtProperties.getAccessExpireIn();
        this.refreshExpiration = jwtProperties.getRefreshExpireIn();
        this.algorithm = Algorithm.HMAC512(new String(jwtProperties.getSecret()).getBytes());
    }

    @Override
    @Transactional
    public AccessTokenResponse getToken(BeltCuttingUserDetails userDetails) {
        RefreshToken newToken = issueRefreshToken(userDetails.getSource());
        return response(userDetails.getUsername(), userDetails.getAuthorities(), newToken);
    }

    @Override
    @Transactional
    public AccessTokenResponse refreshToken(String refreshToken) throws InvalidRefreshTokenException {
        RefreshToken storedToken = refreshTokenRepository.findIfValid(
                verifyRefreshToken(refreshToken),
                OffsetDateTime.now(),
                UserStatus.ACTIVE
        ).orElseThrow(InvalidRefreshTokenException::new);

        checkIfRotated(storedToken);

        BeltCuttingUser user = storedToken.getUser();

        var nextToken = issueRefreshToken(user);

        refreshTokenRepository.updateChain(storedToken, nextToken);

        return response(user.getEmail(), user.getAuthorities().keySet(), nextToken);
    }

    @Override
    @Transactional
    public void invalidateToken(String refreshToken, String ownerEmail) throws InvalidRefreshTokenException {
        RefreshToken storedToken = refreshTokenRepository.findById(verifyRefreshToken(refreshToken))
                .orElseThrow(InvalidRefreshTokenException::new);
        checkOwner(storedToken, ownerEmail);
        checkIfRotated(storedToken);
        refreshTokenRepository.deleteChain(storedToken);

    }

    private void checkOwner(RefreshToken storedToken, String email) throws InvalidRefreshTokenException {
        BeltCuttingUser user = storedToken.getUser();
        if (!user.getEmail().equals(email)) {

            String message = "!! INVESTIGATE ASAP !! User {} engaged in a suspicious activity, " +
                    "trying to use a refresh token issued to another user. " +
                    "Blocking the suspicious actor's account pending investigation!";
            log.error(message, email);
            userRepository.changeStatusByEmail(email, UserStatus.SUSPENDED);

            refreshTokenRepository.deleteChain(storedToken);
            throw new InvalidRefreshTokenException();
        }
    }

    private void checkIfRotated(RefreshToken storedToken) throws InvalidRefreshTokenException {
        if (storedToken.getNext() != null) {
            String message = "!! INVESTIGATE ASAP !! An old refresh token used for user {}, " +
                    "signifying possible token theft! Invalidating the entire token chain.";
            log.error(message, storedToken.getUser().getEmail());
            refreshTokenRepository.deleteChain(storedToken.getNext());
            throw new InvalidRefreshTokenException();
        }
    }

    private RefreshToken issueRefreshToken(BeltCuttingUser user) {
        var refreshToken = new RefreshToken();
        var now = OffsetDateTime.now();
        refreshToken.setIssuedAt(now);
        refreshToken.setExpireAt(now.plus(refreshExpiration));
        refreshToken.setUser(user);
        return refreshTokenRepository.save(refreshToken);
    }

    private AccessTokenResponse response(String subject,
                                         Collection<? extends GrantedAuthority> authorities,
                                         RefreshToken refreshToken) {
        String accessToken = issueJWT(subject, authorities);
        return new AccessTokenResponse(
                accessToken,
                signRefreshToken(refreshToken),
                jwtExpiration.toSeconds()
        );
    }

    private UUID verifyRefreshToken(String refreshJWT) throws InvalidRefreshTokenException {
        try {
            String id = JWT.require(algorithm)
                    .build()
                    .verify(refreshJWT)
                    .getId();
            Objects.requireNonNull(id, "jti must be present in refresh token");
            return UUID.fromString(id);
        } catch (Exception e) {
            throw new InvalidRefreshTokenException(e);
        }
    }

    private String signRefreshToken(RefreshToken token) {
        return JWT.create()
                .withSubject(token.getUser().getEmail())
                .withJWTId(token.getValue().toString())
                .withIssuedAt(Date.from(token.getIssuedAt().toInstant()))
                .withExpiresAt(Date.from(token.getExpireAt().toInstant()))
                .sign(algorithm);
    }

    private String issueJWT(String subject, Collection<? extends GrantedAuthority> authorities) {
        long issuedAt = System.currentTimeMillis();
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date(issuedAt))
                .withExpiresAt(new Date(issuedAt + jwtExpiration.toMillis()))
                .withArrayClaim(SecurityConstants.AUTHORITIES_CLAIM, authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .toArray(String[]::new))
                .sign(algorithm);
    }
}
