package com.syu.capsbe.domain.member.application;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.MemberRepository;
import com.syu.capsbe.domain.member.exception.MemberExistsException;
import com.syu.capsbe.domain.member.exception.common.MemberErrorCode;
import com.syu.capsbe.domain.member.vo.EmailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long save(Member member) {
        return memberRepository.save(member).getId();
    }

    @Override
    public void checkMemberIsExist(String email) {
        if (memberRepository.findByEmail(new EmailVo(email)).isPresent()) {
            throw MemberExistsException.of(MemberErrorCode.MEMBER_IS_ALREADY_EXIST);
        }
    }

    @Override
    public Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> MemberExistsException.of(MemberErrorCode.MEMBER_IS_NOT_EXIST));
    }

    @Override
    public Member findByUuid(String uuid) {
        return memberRepository.findByUuid(uuid)
                .orElseThrow(() -> MemberExistsException.of(MemberErrorCode.MEMBER_IS_NOT_EXIST));
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(new EmailVo(email))
                .orElseThrow(() -> MemberExistsException.of(MemberErrorCode.MEMBER_IS_NOT_EXIST));
    }
}
