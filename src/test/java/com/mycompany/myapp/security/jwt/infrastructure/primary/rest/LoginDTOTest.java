package com.mycompany.myapp.security.jwt.infrastructure.primary.rest;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.UnitTest;
import org.junit.jupiter.api.Test;

@UnitTest
class LoginDTOTest {

  @Test
  void shouldBuild() {
    LoginDTO loginDTO = new LoginDTO();
    loginDTO.setUsername("admin");
    loginDTO.setPassword("password");
    loginDTO.setRememberMe(true);

    assertThat(loginDTO.getUsername()).isEqualTo("admin");
    assertThat(loginDTO.getPassword()).isEqualTo("password");
    assertThat(loginDTO.isRememberMe()).isTrue();

    assertThat(loginDTO.toString()).contains("admin");
    assertThat(loginDTO.toString()).doesNotContain("password");
  }
}
