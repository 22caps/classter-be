package com.syu.capsbe.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "로그인 요청 정보")
@AllArgsConstructor
@NoArgsConstructor
public class SignInEmailResponseDto {

    @Schema(description = "회원 Email", example = "abc@test.com")
    private String email;

    public static SignInEmailResponseDto of(String email) {
        return new SignInEmailResponseDto(email);
    }
}
