package com.iksadnorth.rmsoft.controller;

import com.iksadnorth.rmsoft.dto.request.LoginRequest;
import com.iksadnorth.rmsoft.dto.response.LoginResponse;
import com.iksadnorth.rmsoft.dto.response.MessageResponse;
import com.iksadnorth.rmsoft.dto.request.SignupRequest;
import com.iksadnorth.rmsoft.exception.BookException;
import com.iksadnorth.rmsoft.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/members")
    public MessageResponse signup(
            @RequestBody SignupRequest request
    ) throws BookException {
        return memberService.signup(request);
    }

    @PostMapping("/api/auth/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) throws BookException {
        return memberService.login(request);
    }
}
