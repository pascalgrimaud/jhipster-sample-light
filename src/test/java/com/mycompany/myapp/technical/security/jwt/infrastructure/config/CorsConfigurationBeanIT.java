package com.mycompany.myapp.technical.security.jwt.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.cors.CorsConfiguration;

@SpringBootTest
class CorsConfigurationBeanIT {

  @Autowired
  CorsConfiguration corsConfiguration;

  @Test
  void shouldGetCorsAllowedOrigins() {
    assertThat(corsConfiguration.getAllowedOrigins())
      .containsExactlyInAnyOrderElementsOf(List.of("http://localhost:8100", "http://localhost:9000", "http://test:9000"));
  }
}
