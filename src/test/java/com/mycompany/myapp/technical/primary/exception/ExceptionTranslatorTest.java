package com.mycompany.myapp.technical.primary.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.zalando.problem.Status.BAD_REQUEST;

import com.mycompany.myapp.UnitTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;
import org.zalando.problem.violations.Violation;

@UnitTest
@ExtendWith(SpringExtension.class)
class ExceptionTranslatorTest {

  @Mock
  NativeWebRequest request;

  @InjectMocks
  ExceptionTranslator exceptionTranslator;

  @Test
  void shouldProcessNullEntity() {
    assertThat(exceptionTranslator.process(null, request)).isNull();
  }

  @Test
  void shouldProcessConstraintViolationProblem() {
    // Given
    List<Violation> violations = List.of();
    ConstraintViolationProblem problem = new ConstraintViolationProblem(BAD_REQUEST, violations);

    // When
    ResponseEntity<Problem> result = exceptionTranslator.process(ResponseEntity.badRequest().body(problem), request);

    // Then
    assertThat(result).isNotNull();
    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(result.getStatusCodeValue()).isEqualTo(400);
    assertThat(result.getBody()).isNotNull();
    assertThat(result.getBody().getTitle()).isEqualTo("Constraint Violation");
  }

  @Test
  void shouldGetMessageWithDefaultMessage() {
    FieldError f = new FieldError("test", "test", "defaultMessage");
    String result = ReflectionTestUtils.invokeMethod(exceptionTranslator, "getMessage", f);
    assertThat(result).isEqualTo("defaultMessage");
  }

  @Test
  void shouldGetMessageWithCode() {
    FieldError f = new FieldError("test", "test", null, false, new String[] { "400" }, null, null);
    String result = ReflectionTestUtils.invokeMethod(exceptionTranslator, "getMessage", f);
    assertThat(result).isEqualTo("400");
  }
}
