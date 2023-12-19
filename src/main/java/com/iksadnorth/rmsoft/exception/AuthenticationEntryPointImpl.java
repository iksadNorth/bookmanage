package com.iksadnorth.rmsoft.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iksadnorth.rmsoft.dto.response.MessageResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        String message = "인증 오류 발생";
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        response.setStatus(httpStatus.value());
        log.warn(message);

        MessageResponse responseDto = new MessageResponse(message);
        objectMapper.writeValue(response.getWriter(), responseDto);
    }
}
