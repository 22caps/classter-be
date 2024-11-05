package com.syu.capsbe.domain.auth.application;

import com.syu.capsbe.domain.auth.dto.request.SignInEmailRequestDto;
import com.syu.capsbe.domain.auth.dto.response.SignInEmailResponseDto;
import com.syu.capsbe.domain.auth.dto.request.SignInRequestDto;
import com.syu.capsbe.domain.auth.dto.request.SignUpRequestDto;
import com.syu.capsbe.domain.auth.dto.response.SignInResponseDto;
import com.syu.capsbe.domain.auth.dto.response.SignUpResponseDto;

public interface AuthService {

    SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto);

    SignInResponseDto signIn(SignInRequestDto signInRequestDto);

    SignInEmailResponseDto signInWithEmail(SignInEmailRequestDto signInRequestDto);
}
