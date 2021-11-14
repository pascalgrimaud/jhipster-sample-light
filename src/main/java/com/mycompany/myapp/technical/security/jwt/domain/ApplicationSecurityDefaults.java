package com.mycompany.myapp.technical.security.jwt.domain;
/**
 * <p>JHipsterDefaults interface.</p>
 */
@SuppressWarnings("java:S2386")
public interface ApplicationSecurityDefaults {

  interface Security {
    String contentSecurityPolicy = "default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:";

    interface Authentication {

      interface Jwt {

        String secret = null;
        String base64Secret = null;
        long tokenValidityInSeconds = 1800; // 30 minutes
        long tokenValidityInSecondsForRememberMe = 2592000; // 30 days
      }
    }
  }
}
