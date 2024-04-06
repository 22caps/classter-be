package com.syu.capsbe.domain.auth.presentation;

import com.syu.capsbe.domain.auth.application.AuthService;
import com.syu.capsbe.domain.auth.dto.request.SignInRequestDto;
import com.syu.capsbe.domain.auth.dto.request.SignUpRequestDto;
import com.syu.capsbe.domain.auth.dto.response.SignInResponseDto;
import com.syu.capsbe.domain.auth.dto.response.SignUpResponseDto;
import com.syu.capsbe.domain.member.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "회원 인증 도메인 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원 가입", description = "이메일을 통해 회원 가입 진행합니다.")
    @ApiResponse(responseCode = "201", description = "회원 가입 성공")
    public SignUpResponseDto signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        return this.authService.signUp(signUpRequestDto);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = "UUID를 통해 로그인을 진행합니다.")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    public SignInResponseDto signIn(@RequestBody @Valid SignInRequestDto signInRequestDto) {
        return this.authService.signIn(signInRequestDto);
    }

    @GetMapping("/test")
    @Operation(summary = "테스트", description = "인증된 사용자만 접근 가능한 테스트 API")
    @ApiResponse(responseCode = "200", description = "테스트 성공")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Map<String, String> hello(@AuthenticationPrincipal Member member) {
        return Map.of("email", member.getEmail().getEmail());
    }
}
