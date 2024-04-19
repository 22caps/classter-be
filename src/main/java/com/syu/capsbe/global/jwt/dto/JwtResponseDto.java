package com.syu.capsbe.global.jwt.dto;

import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtResponseDto {

    private final String accessToken;

    private final Date expiresAt;

    public static JwtResponseDto of(String accessToken, Date expiresAt) {
        return new JwtResponseDto(accessToken, expiresAt);
    }
}
