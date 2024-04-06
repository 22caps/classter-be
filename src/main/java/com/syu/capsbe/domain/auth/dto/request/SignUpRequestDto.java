package com.syu.capsbe.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "회원가입 요청 정보")
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

    @Schema(description = "이메일", example = "test@gmail.com")
    private String email;
}
