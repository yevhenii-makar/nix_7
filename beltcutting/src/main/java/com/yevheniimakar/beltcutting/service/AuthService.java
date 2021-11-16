package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.exceptions.auth.InvalidRefreshTokenException;
import com.yevheniimakar.beltcutting.model.auth.BeltCuttingUserDetails;
import com.yevheniimakar.beltcutting.model.auth.response.AccessTokenResponse;

public interface AuthService {

    AccessTokenResponse getToken(BeltCuttingUserDetails userDetails);

    AccessTokenResponse refreshToken(String refreshToken)
            throws InvalidRefreshTokenException;

    void invalidateToken(String refreshToken, String ownerEmail) throws InvalidRefreshTokenException;

}
