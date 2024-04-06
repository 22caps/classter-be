package com.syu.capsbe.domain.auth.application;

import com.syu.capsbe.domain.auth.dto.request.SignInRequestDto;
import com.syu.capsbe.domain.auth.dto.request.SignUpRequestDto;
import com.syu.capsbe.domain.auth.dto.response.SignInResponseDto;
import com.syu.capsbe.domain.auth.dto.response.SignUpResponseDto;
import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.MemberRepository;
import com.syu.capsbe.domain.member.vo.EmailVo;
import com.syu.capsbe.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;


    @Override
    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        checkMemberIsExist(signUpRequestDto.getEmail()); // 이미 존재하는 회원인지 확인

        Member member = Member.builder()
                .email(signUpRequestDto.getEmail())
                .build();

        // 회원 저장
        Long id = memberRepository.save(member).getId();

        // 이메일 전송
        emailService.sendEmail(signUpRequestDto.getEmail(), "회원가입용 UUID 전송", member.getUuid());

        return SignUpResponseDto.of(id, member.getUuid());
    }

    @Override
    @Transactional
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        // uuid로 회원 검색
        Member member = memberRepository.findByUuid(signInRequestDto.getUuid())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // accessToken 생성 by member email, member role
        String accessToken = jwtProvider.generateToken(member.getUsername(), member.getRoles());

        return SignInResponseDto.of(accessToken);
    }

    private void checkMemberIsExist(String email) {
        if (memberRepository.findByEmail(new EmailVo(email)).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }
}
