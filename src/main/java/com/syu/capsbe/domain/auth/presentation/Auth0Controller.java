package com.syu.capsbe.domain.auth.presentation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.*;
import java.net.URI;

@Controller
public class Auth0Controller {

    @Value("${auth0.domain}")
    private String auth0Domain;

    @Value("${auth0.clientId}")
    private String clientId;

    @Value("${auth0.callbackUrl}")
    private String redirectUri;

    @GetMapping("/auth")
    public void authProxy(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String queryString = request.getQueryString();

        String authorizationEndpoint = "https://" + auth0Domain + "/authorize";

        URI redirectUrl = UriComponentsBuilder.fromHttpUrl(authorizationEndpoint)
                .query(queryString)
                .build(true)
                .toUri();

        response.sendRedirect(redirectUrl.toString());
    }

    @PostMapping("/token")
    public ResponseEntity<String> tokenProxy(HttpServletRequest request) throws Exception {
        String tokenEndpoint = "https://" + auth0Domain + "/oauth/token";

        // 요청의 form 데이터를 그대로 전달
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 요청 파라미터 가져오기
        String formData = request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);

        HttpEntity<String> entity = new HttpEntity<>(formData, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                tokenEndpoint,
                HttpMethod.POST,
                entity,
                String.class
        );

        return ResponseEntity.status(response.getStatusCode())
                .body(response.getBody());
    }
}
