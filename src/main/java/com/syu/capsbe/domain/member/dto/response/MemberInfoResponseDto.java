package com.syu.capsbe.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "메인 화면에 노출될 회원 정보")
public class MemberInfoResponseDto {

    private final String email;

    private final int goalScore;

    @Builder
    public static MemberInfoResponseDto of(String email, int goalScore) {
        return new MemberInfoResponseDto(email, goalScore);
    }
}
