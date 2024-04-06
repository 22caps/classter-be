package com.syu.capsbe.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원가입 응답 정보")
public class SignUpResponseDto {

    @Schema(description = "생성된 회원 번호", example = "1")
    private final Long id;

    @Schema(description = "생성된 회원 UUID", example = "daa2e061-bb8f-462f-b4f5-d64150b007f8")
    private final String uuid;

    public static SignUpResponseDto of(Long id, String uuid) {
        return new SignUpResponseDto(id, uuid);
    }
}
