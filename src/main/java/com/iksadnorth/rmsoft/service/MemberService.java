package com.iksadnorth.rmsoft.service;

import com.iksadnorth.rmsoft.dto.request.LoginRequest;
import com.iksadnorth.rmsoft.dto.response.LoginResponse;
import com.iksadnorth.rmsoft.dto.response.MessageResponse;
import com.iksadnorth.rmsoft.dto.request.SignupRequest;
import com.iksadnorth.rmsoft.exception.BookException;

public interface MemberService {
    MessageResponse signup(SignupRequest request) throws BookException;

    LoginResponse login(LoginRequest request) throws BookException;
}
