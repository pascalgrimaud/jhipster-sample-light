package com.mycompany.myapp.security.jwt.infrastructure.config;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.IntegrationTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.filter.CorsFilter;

@IntegrationTest
class CorsFilterConfigurationIT {

  @Nested
  @SpringBootTest(
    properties = {
      "application.cors.allowed-origins=http://localhost:8100,http://localhost:9000", "application.cors.allowed-origin-patterns=",
    }
  )
  class CorsFilterDefault {

    @Autowired
    CorsFilter corsFilter;

    @Test
    void shouldCorsFilter() {
      assertThat(corsFilter).isNotNull();
    }
  }

  @Nested
  @SpringBootTest(properties = { "application.cors.allowed-origins=", "application.cors.allowed-origin-patterns=" })
  class CorsFilterEmpty {

    @Autowired
    CorsFilter corsFilter;

    @Test
    void shouldCorsFilter() {
      assertThat(corsFilter).isNotNull();
    }
  }

  @Nested
  @SpringBootTest(
    properties = {
      "application.cors.allowed-origins=", "application.cors.allowed-origin-patterns=http://localhost:8100,http://localhost:9000",
    }
  )
  class CorsFilterWithAllowedOriginPatterns {

    @Autowired
    CorsFilter corsFilter;

    @Test
    void shouldCorsFilter() {
      assertThat(corsFilter).isNotNull();
    }
  }
}
