package com.itransition.backend.authencation;

public interface TokenService {

    TokenResponse createTokenResponse(Long userId);

    TokenResponse refreshTokens(String token);

}
