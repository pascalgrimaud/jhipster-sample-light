package com.mycompany.myapp.technical.security.jwt.infrastructure.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.filter.CorsFilter;

@SpringBootTest
class CorsFilterBeanIT {

  @Autowired
  CorsFilter corsFilter;

  @Test
  void shouldCorsFilter() {
    assertThat(corsFilter).isNotNull();
  }
}
