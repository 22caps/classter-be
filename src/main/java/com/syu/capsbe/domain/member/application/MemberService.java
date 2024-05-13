package com.syu.capsbe.domain.member.application;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.dto.request.MemberUpdateRequestDto;
import com.syu.capsbe.domain.member.dto.response.CorrectResponseDto;
import com.syu.capsbe.domain.member.dto.response.MemberInfoResponseDto;
import java.util.List;

public interface MemberService {

    Long save(Member member);

    void checkMemberIsExist(String email);

    Member findByMemberId(Long memberId);

    Member findByUuid(String uuid);

    Member findByEmail(String email);

    MemberInfoResponseDto getMemberInfo(Long memberId);

    MemberInfoResponseDto updateMember(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto);

    List<CorrectResponseDto> getWeeklyPercentCorrect(Long memberId);
}
