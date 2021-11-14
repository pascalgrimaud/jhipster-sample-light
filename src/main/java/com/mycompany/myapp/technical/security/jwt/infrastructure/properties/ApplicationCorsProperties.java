package com.mycompany.myapp.technical.security.jwt.infrastructure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@ConfigurationProperties(prefix = "application.cors", ignoreUnknownFields = false)
public class ApplicationCorsProperties {

  private final CorsConfiguration cors = new CorsConfiguration();

  public CorsConfiguration getCors() {
    return cors;
  }
}
