package com.syu.capsbe.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "로그인 응답 정보")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInResponseDto {

    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTcxMjIwOTc1MSwiZXhwIjoxNzE0ODAxNzUxfQ.RWRVAbvsM_Use6fwSGuMZ_FFa79xsGBrCwE-gYs3EGI")
    private final String accessToken;

    public static SignInResponseDto of(String accessToken) {
        return new SignInResponseDto(accessToken);
    }
}
