package com.syu.capsbe.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원 주간 정답률")
public class CorrectResponseDto {

    private LocalDate solveDate;
    private String accuracyRate;
    private String day;
}
