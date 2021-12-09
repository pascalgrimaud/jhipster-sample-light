package com.mycompany.myapp.security.jwt.infrastructure.config;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@UnitTest
class ApplicationSecurityPropertiesTest {

  private ApplicationSecurityProperties properties;

  @BeforeEach
  void setup() {
    properties = new ApplicationSecurityProperties();
  }

  @Test
  void testSecurityAuthenticationJwtSecret() {
    ApplicationSecurityProperties.Authentication.Jwt obj = properties.getAuthentication().getJwt();
    String val = ApplicationSecurityDefaults.Security.Authentication.Jwt.secret;
    assertThat(obj.getSecret()).isEqualTo(val);
    val = "1" + val;
    obj.setSecret(val);
    assertThat(obj.getSecret()).isEqualTo(val);
  }

  @Test
  void testSecurityAuthenticationJwtBase64Secret() {
    ApplicationSecurityProperties.Authentication.Jwt obj = properties.getAuthentication().getJwt();
    String val = ApplicationSecurityDefaults.Security.Authentication.Jwt.base64Secret;
    assertThat(obj.getSecret()).isEqualTo(val);
    val = "1" + val;
    obj.setBase64Secret(val);
    assertThat(obj.getBase64Secret()).isEqualTo(val);
  }

  @Test
  void testSecurityAuthenticationJwtTokenValidityInSeconds() {
    ApplicationSecurityProperties.Authentication.Jwt obj = properties.getAuthentication().getJwt();
    long val = ApplicationSecurityDefaults.Security.Authentication.Jwt.tokenValidityInSeconds;
    assertThat(obj.getTokenValidityInSeconds()).isEqualTo(val);
    val++;
    obj.setTokenValidityInSeconds(val);
    assertThat(obj.getTokenValidityInSeconds()).isEqualTo(val);
  }

  @Test
  void testSecurityAuthenticationJwtTokenValidityInSecondsForRememberMe() {
    ApplicationSecurityProperties.Authentication.Jwt obj = properties.getAuthentication().getJwt();
    long val = ApplicationSecurityDefaults.Security.Authentication.Jwt.tokenValidityInSecondsForRememberMe;
    assertThat(obj.getTokenValidityInSecondsForRememberMe()).isEqualTo(val);
    val++;
    obj.setTokenValidityInSecondsForRememberMe(val);
    assertThat(obj.getTokenValidityInSecondsForRememberMe()).isEqualTo(val);
  }

  @Test
  void testSecurityContentSecurityPolicy() {
    ApplicationSecurityProperties obj = properties;
    String val = ApplicationSecurityDefaults.Security.contentSecurityPolicy;
    assertThat(obj.getContentSecurityPolicy()).isEqualTo(val);
    obj.setContentSecurityPolicy("foobar");
    assertThat(obj.getContentSecurityPolicy()).isEqualTo("foobar");
  }
}
