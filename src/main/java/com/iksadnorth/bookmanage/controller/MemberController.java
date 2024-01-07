package com.iksadnorth.bookmanage.controller;

import com.iksadnorth.bookmanage.dto.request.LoginRequest;
import com.iksadnorth.bookmanage.dto.response.LoginResponse;
import com.iksadnorth.bookmanage.dto.response.MessageResponse;
import com.iksadnorth.bookmanage.dto.request.SignupRequest;
import com.iksadnorth.bookmanage.exception.BookException;
import com.iksadnorth.bookmanage.service.MemberService;
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
