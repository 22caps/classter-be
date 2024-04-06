package com.syu.capsbe.domain.auth.application;

public interface EmailService {

    void sendEmail(String email, String title, String message);
}
