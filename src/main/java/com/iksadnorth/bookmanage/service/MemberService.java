package com.iksadnorth.bookmanage.service;

import com.iksadnorth.bookmanage.dto.request.LoginRequest;
import com.iksadnorth.bookmanage.dto.response.LoginResponse;
import com.iksadnorth.bookmanage.dto.response.MessageResponse;
import com.iksadnorth.bookmanage.dto.request.SignupRequest;
import com.iksadnorth.bookmanage.exception.BookException;

public interface MemberService {
    MessageResponse signup(SignupRequest request) throws BookException;

    LoginResponse login(LoginRequest request) throws BookException;
}
