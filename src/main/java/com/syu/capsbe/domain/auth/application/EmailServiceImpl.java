package com.syu.capsbe.domain.auth.application;

import com.syu.capsbe.domain.auth.exception.AuthEmailExistenceException;
import com.syu.capsbe.domain.auth.exception.common.AuthErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toEmail, String title, String text) {
        // 이메일 전송 로직
        SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
        try {
            javaMailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.debug("MailService.sendEmail exception occur toEmail: {}, " +
                    "title: {}, text: {}", toEmail, title, text);
            throw AuthEmailExistenceException.of(AuthErrorCode.EMAIL_SENDING_ERROR);
        }
    }

    // 발신할 이메일 데이터 세팅
    private SimpleMailMessage createEmailForm(String toEmail,
            String title,
            String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }
}
