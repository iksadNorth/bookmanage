package com.iksadnorth.bookmanage.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iksadnorth.bookmanage.dto.response.MessageResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        String message = "인가 오류 발생";
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        response.setStatus(httpStatus.value());
        log.warn(message);

        MessageResponse responseDto = new MessageResponse(message);
        objectMapper.writeValue(response.getWriter(), responseDto);
    }
}
