package com.iksadnorth.rmsoft.exception;

import com.iksadnorth.rmsoft.dto.response.MessageResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Component
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(BookException.class)
    public MessageResponse handleIllegalArgumentException(HttpServletResponse response, BookException ex) {
        response.setStatus(ex.getStatus().value());

        log.warn(ex.getMessage());
        return new MessageResponse(ex.getMessage());
    }
}
