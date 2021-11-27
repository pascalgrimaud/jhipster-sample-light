package com.mycompany.myapp.security.jwt.infrastructure.config;

import com.mycompany.myapp.technical.infrastructure.primary.exception.ExceptionTranslator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

@ControllerAdvice
public class SecurityExceptionTranslator extends ExceptionTranslator implements SecurityAdviceTrait {}
