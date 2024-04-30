package com.syu.capsbe.domain.member.presentation;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.application.MemberService;
import com.syu.capsbe.domain.member.dto.request.MemberUpdateRequestDto;
import com.syu.capsbe.domain.member.dto.response.MemberInfoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "Member", description = "회원 도메인 API")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @Operation(summary = "회원 정보 조회", description = "메인화면에서 노출될 회원 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공")
    @ApiResponse(responseCode = "E1002", description = "존재하지 않는 회원입니다.")
    public MemberInfoResponseDto getMemberInfo(@AuthenticationPrincipal Member member) {
        return memberService.getMemberInfo(member.getId());
    }

    @PatchMapping
    @Operation(summary = "회원 정보 수정", description = "회원 정보(목표 점수)를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공")
    @ApiResponse(responseCode = "E1002", description = "존재하지 않는 회원입니다.")
    @ApiResponse(responseCode = "E1004", description = "목표 점수는 100점 이상 990점 이하로 설정할 수 있습니다.")
    public MemberInfoResponseDto updateMemberInfo(@AuthenticationPrincipal Member member,
            @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        return memberService.updateMember(member.getId(), memberUpdateRequestDto);
    }
}
