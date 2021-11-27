package com.mycompany.myapp.technical.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.security", ignoreUnknownFields = false)
public class ApplicationSecurityProperties {

  private String contentSecurityPolicy = ApplicationSecurityDefaults.Security.contentSecurityPolicy;

  private final Authentication authentication = new Authentication();

  public Authentication getAuthentication() {
    return authentication;
  }

  public String getContentSecurityPolicy() {
    return contentSecurityPolicy;
  }

  public void setContentSecurityPolicy(String contentSecurityPolicy) {
    this.contentSecurityPolicy = contentSecurityPolicy;
  }

  public static class Authentication {

    private final Jwt jwt = new Jwt();

    public Jwt getJwt() {
      return jwt;
    }

    public static class Jwt {

      private String secret = ApplicationSecurityDefaults.Security.Authentication.Jwt.secret;

      private String base64Secret = ApplicationSecurityDefaults.Security.Authentication.Jwt.base64Secret;

      private long tokenValidityInSeconds = ApplicationSecurityDefaults.Security.Authentication.Jwt.tokenValidityInSeconds;

      private long tokenValidityInSecondsForRememberMe =
        ApplicationSecurityDefaults.Security.Authentication.Jwt.tokenValidityInSecondsForRememberMe;

      public String getSecret() {
        return secret;
      }

      public void setSecret(String secret) {
        this.secret = secret;
      }

      public String getBase64Secret() {
        return base64Secret;
      }

      public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
      }

      public long getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
      }

      public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
        this.tokenValidityInSeconds = tokenValidityInSeconds;
      }

      public long getTokenValidityInSecondsForRememberMe() {
        return tokenValidityInSecondsForRememberMe;
      }

      public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
        this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
      }
    }
  }
}
