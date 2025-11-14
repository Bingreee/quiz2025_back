package com.example.quiz2025.quiz2025src.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secret;
    private long accessTokenExpirationTime;
    private long refreshTokenExpirationTime;
    private String tokenPrefix;
    private String headerString;
}
