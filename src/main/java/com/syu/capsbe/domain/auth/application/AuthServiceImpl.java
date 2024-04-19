package com.syu.capsbe.domain.auth.application;

import com.syu.capsbe.domain.auth.dto.request.SignInRequestDto;
import com.syu.capsbe.domain.auth.dto.request.SignUpRequestDto;
import com.syu.capsbe.domain.auth.dto.response.SignInResponseDto;
import com.syu.capsbe.domain.auth.dto.response.SignUpResponseDto;
import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.application.MemberService;
import com.syu.capsbe.global.jwt.JwtProvider;
import com.syu.capsbe.global.jwt.dto.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final MemberService memberService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        memberService.checkMemberIsExist(signUpRequestDto.getEmail());

        Member member = Member.builder()
                .email(signUpRequestDto.getEmail())
                .build();

        Long id = memberService.save(member);

        emailService.sendEmail(signUpRequestDto.getEmail(), "서비스 사용을 위한 UUID입니다!",
                member.getUuid());

        return SignUpResponseDto.of(id, member.getUuid());
    }

    @Override
    @Transactional
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        Member member = memberService.findByUuid(signInRequestDto.getUuid());

        JwtResponseDto jwtResponseDto = jwtProvider.generateToken(member.getUsername(),
                member.getRoles());

        return SignInResponseDto.of(jwtResponseDto.getAccessToken(), jwtResponseDto.getExpiresAt());
    }
}
