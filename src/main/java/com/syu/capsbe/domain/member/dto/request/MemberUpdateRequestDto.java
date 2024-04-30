package com.syu.capsbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "회원 정보 수정 요청 정보")
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateRequestDto {

    @Schema(description = "목표 점수", example = "800")
    private int goalScore;
}
