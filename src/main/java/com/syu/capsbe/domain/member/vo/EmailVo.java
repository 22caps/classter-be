package com.syu.capsbe.domain.member.vo;

import com.syu.capsbe.domain.member.exception.MemberEmailInvalidException;
import com.syu.capsbe.domain.member.exception.common.MemberErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class EmailVo {

    private static final String EMAIL_REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-z]+[.]+[a-z]{2,3}$";

    @Column(name = "email", nullable = false)
    private String email;

    public EmailVo(String email) {
        if (!isValidEmail(email)) {
            throw MemberEmailInvalidException.of(MemberErrorCode.INVALID_EMAIL_FORMAT);
        }
        this.email = email;
    }

    private boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }
}
