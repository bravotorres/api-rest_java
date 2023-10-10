package com.quironlabs.api.models.entities.responses;

import org.springframework.beans.factory.annotation.Value;


public class JwtResponse {
    private String access_token;
    private String token_type;

    @Value("${app.jwt.expire.segs}")
    private Integer expires_in;
    private String scope;

    public JwtResponse(String accessToken) {
        this(accessToken, "bearer", 180, "basic");
    }

    public JwtResponse(String accessToken, String tokenType, Integer expiresIn, String scope) {
        this.access_token = accessToken;
        this.token_type = tokenType;
        this.expires_in = expiresIn;
        this.scope = scope;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String tokenType) {
        this.token_type = tokenType;
    }

    public Integer getExpiresIn() {
        return expires_in;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expires_in = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "JwtResponse [access_token=" + this.getAccessToken() + 
            ", token_type=" + this.getTokenType() + ", expires_in=" + this.getExpiresIn()
            + ", scope=" + scope + "]";
    }

}
