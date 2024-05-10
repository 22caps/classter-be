package com.syu.capsbe.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원 주간 정답률")
public class CorrectResponseDto {

    private LocalDateTime solveDate;
    private String day;
    private Double accuracyRate;



}
