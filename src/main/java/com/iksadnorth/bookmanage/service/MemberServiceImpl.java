package com.iksadnorth.bookmanage.service;

import com.iksadnorth.bookmanage.domain.Member;
import com.iksadnorth.bookmanage.dto.request.LoginRequest;
import com.iksadnorth.bookmanage.dto.response.LoginResponse;
import com.iksadnorth.bookmanage.dto.response.MessageResponse;
import com.iksadnorth.bookmanage.dto.request.SignupRequest;
import com.iksadnorth.bookmanage.exception.BookException;
import com.iksadnorth.bookmanage.jwt.JwtUtil;
import com.iksadnorth.bookmanage.property.SecurityAdminProperties;
import com.iksadnorth.bookmanage.repository.MemberRepository;
import com.iksadnorth.bookmanage.type.MemberRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final SecurityAdminProperties securityAdminProperties;

    @Transactional
    @Override
    public MessageResponse signup(SignupRequest request) throws BookException {
        String username = request.getUsername();
        String password = request.getPassword();
        String adminKey = request.getAdminKey();

        // 존재여부 확인. 있다면 오류
        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        if (memberOptional.isPresent()) {
            throw new BookException("이미 해당 username으로 회원가입되어 있습니다.", HttpStatus.CONFLICT);
        }

        // 회원 등록
        Member entity = new Member();
        entity.setUsername(username);
        entity.setPassword(
                passwordEncoder.encode(password)
        );

        // adminKey 일치 시, 관리자 권한 부여.
        boolean isAdmin = adminKey != null && adminKey.equals(securityAdminProperties.getKey());
        MemberRoleType roleType = isAdmin ? MemberRoleType.ROLE_ADMIN : MemberRoleType.ROLE_USER;
        entity.setRoleType(roleType);

        memberRepository.save(entity);

        return new MessageResponse(
                "회원 가입이 성공적으로 동작했습니다."
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) throws BookException {
        String username = request.getUsername();
        String passwordInRequest = request.getPassword();

        // 존재여부 확인. 없다면 오류
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new BookException("해당 username의 회원 정보는 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        // 비밀번호 확인. 틀렸다면 오류.
        String passwordInDb = member.getPassword();
        if (!passwordEncoder.matches(passwordInRequest, passwordInDb)) {
            throw new BookException("password가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // JWT 발급
        String token = jwtUtil.createToken(username);

        return new LoginResponse(
                "로그인에 성공했습니다.",
                token
        );
    }
}
