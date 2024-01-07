package com.iksadnorth.bookmanage.repository;

import com.iksadnorth.bookmanage.domain.Member;
import com.iksadnorth.bookmanage.type.MemberRoleType;
import com.iksadnorth.bookmanage.utils.LoadTestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @LoadTestCase
    @Test
    void runNormally_save() {
        // given
        Member member = new Member();
        member.setUsername("test");
        member.setPassword("q1w2e3r4");
        member.setRoleType(MemberRoleType.ROLE_USER);

        // when
        memberRepository.save(member);

        // then
        Optional<Member> memberOptional = memberRepository.findByUsername("test");
        assertTrue(memberOptional.isPresent());
        log.info((memberOptional.get().toString()));
    }

    @LoadTestCase
    @Test
    void runNormally_findByUsername() {
        // given
        String username = "username 1";

        // when
        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        // then
        assertTrue(memberOptional.isPresent());
        log.info((memberOptional.get().toString()));
    }
}