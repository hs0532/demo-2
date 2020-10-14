package com.haoshuang.web.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class SsoClient3Application {
    public static void main(String[] args) {
        SpringApplication.run(SsoClient3Application.class, args);
    }

    @GetMapping("/user")
    public Authentication user(Authentication user){
        return user;
    }

}
