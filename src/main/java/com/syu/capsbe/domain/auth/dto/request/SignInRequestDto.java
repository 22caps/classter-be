package com.syu.capsbe.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "로그인 요청 정보")
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto {

    @Schema(description = "회원 UUID", example = "daa2e061-bb8f-462f-b4f5-d64150b007f8")
    private String uuid;
}
