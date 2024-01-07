package com.iksadnorth.bookmanage.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookException extends Throwable {
    private final HttpStatus status;

    public BookException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
