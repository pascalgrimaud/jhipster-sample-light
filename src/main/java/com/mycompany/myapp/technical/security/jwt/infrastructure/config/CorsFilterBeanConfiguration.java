package com.mycompany.myapp.technical.security.jwt.infrastructure.config;

import static java.net.URLDecoder.decode;

import com.mycompany.myapp.technical.security.jwt.infrastructure.properties.ApplicationCorsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsFilterBeanConfiguration {

  private final Logger log = LoggerFactory.getLogger(CorsFilterBeanConfiguration.class);

  private final Environment env;
  private final ApplicationCorsProperties applicationCorsProperties;

  public CorsFilterBeanConfiguration(Environment env, ApplicationCorsProperties applicationCorsProperties) {
    this.env = env;
    this.applicationCorsProperties = applicationCorsProperties;
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = applicationCorsProperties.getCors();
    if (!CollectionUtils.isEmpty(config.getAllowedOrigins()) || !CollectionUtils.isEmpty(config.getAllowedOriginPatterns())) {
      log.debug("Registering CORS filter");
      source.registerCorsConfiguration("/api/**", config);
      source.registerCorsConfiguration("/management/**", config);
      source.registerCorsConfiguration("/v2/api-docs", config);
      source.registerCorsConfiguration("/v3/api-docs", config);
      source.registerCorsConfiguration("/swagger-resources", config);
      source.registerCorsConfiguration("/swagger-ui/**", config);
    }
    return new CorsFilter(source);
  }
}
