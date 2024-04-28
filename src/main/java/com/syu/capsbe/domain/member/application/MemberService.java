package com.syu.capsbe.domain.member.application;

import com.syu.capsbe.domain.member.Member;

public interface MemberService {

    Long save(Member member);

    void checkMemberIsExist(String email);

    Member findByMemberId(Long memberId);

    Member findByUuid(String uuid);

    Member findByEmail(String email);
}
