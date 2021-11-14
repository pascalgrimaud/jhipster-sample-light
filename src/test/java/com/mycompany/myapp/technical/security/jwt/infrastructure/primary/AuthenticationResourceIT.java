package com.mycompany.myapp.technical.security.jwt.infrastructure.primary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class AuthenticationResourceIT {

  static final String TEST_USER_LOGIN = "test";

  @Autowired
  MockMvc mockMvc;

  @Test
  @WithUnauthenticatedMockUser
  void testNonAuthenticatedUser() throws Exception {
    mockMvc.perform(get("/api/authenticate").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(""));
  }

  @Test
  @WithMockUser(value = AuthenticationResourceIT.TEST_USER_LOGIN)
  void testAuthenticatedUser() throws Exception {
    mockMvc
      .perform(
        get("/api/authenticate")
          .with(request -> {
            request.setRemoteUser(TEST_USER_LOGIN);
            return request;
          })
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(content().string(TEST_USER_LOGIN));
  }

  @Test
  void testAuthorize() throws Exception {
    LoginVM login = new LoginVM();
    login.setUsername("admin");
    login.setPassword("admin");
    mockMvc
      .perform(post("/api/authenticate").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(login)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id_token").isString())
      .andExpect(jsonPath("$.id_token").isNotEmpty())
      .andExpect(header().string("Authorization", not(nullValue())))
      .andExpect(header().string("Authorization", not(is(emptyString()))));
  }

  @Test
  void testAuthorizeWithRememberMe() throws Exception {
    LoginVM login = new LoginVM();
    login.setUsername("admin");
    login.setPassword("admin");
    login.setRememberMe(true);
    mockMvc
      .perform(post("/api/authenticate").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(login)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id_token").isString())
      .andExpect(jsonPath("$.id_token").isNotEmpty())
      .andExpect(header().string("Authorization", not(nullValue())))
      .andExpect(header().string("Authorization", not(is(emptyString()))));
  }

  @Test
  void testAuthorizeFails() throws Exception {
    LoginVM login = new LoginVM();
    login.setUsername("admin");
    login.setPassword("wrong password");
    mockMvc
      .perform(post("/api/authenticate").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(login)))
      .andExpect(status().isUnauthorized())
      .andExpect(jsonPath("$.id_token").doesNotExist())
      .andExpect(header().doesNotExist("Authorization"));
  }
}
