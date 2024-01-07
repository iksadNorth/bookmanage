package com.iksadnorth.bookmanage.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class LoginResponse {
    private final String message;
    private final String token;
}
