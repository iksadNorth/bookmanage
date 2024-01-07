package com.iksadnorth.bookmanage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iksadnorth.bookmanage.dto.request.LoginRequest;
import com.iksadnorth.bookmanage.dto.request.SignupRequest;
import com.iksadnorth.bookmanage.utils.LoadTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class MemberControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @LoadTestCase
    @DisplayName("[정상 작동] Post /api/members - 일반 회원으로 가입")
    @Test
    void runNormally_signup_asUser() throws Exception {
        // given
        SignupRequest request = new SignupRequest();
        request.setUsername("username 0");
        request.setPassword("q1w2e3r4");
        request.setAdminKey(null);

        MockHttpServletRequestBuilder builder = post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @LoadTestCase
    @DisplayName("[정상 작동] Post /api/members - 관리자로 가입")
    @Test
    void runNormally_signup_asAdmin() throws Exception {
        // given
        SignupRequest request = new SignupRequest();
        request.setUsername("username 0");
        request.setPassword("q1w2e3r4");
        request.setAdminKey("12345");

        MockHttpServletRequestBuilder builder = post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @LoadTestCase
    @DisplayName("[비정상 작동] Post /api/members - 중복되는 username")
    @Test
    void runAbnormally_signup_duplicatedUsername() throws Exception {
        // given
        SignupRequest request = new SignupRequest();
        request.setUsername("username 1");
        request.setPassword("q1w2e3r4");

        MockHttpServletRequestBuilder builder = post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @LoadTestCase
    @DisplayName("[정상 작동] Post /api/auth/login")
    @Test
    void runNormally_login() throws Exception {
        // given
        LoginRequest request = new LoginRequest();
        request.setUsername("username 1");
        request.setPassword("asdf");

        MockHttpServletRequestBuilder builder = post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @LoadTestCase
    @DisplayName("[비정상 작동] Post /api/auth/login - 존재하지 않는 username")
    @Test
    void runAbnormally_login_nonExistedUsername() throws Exception {
        // given
        LoginRequest request = new LoginRequest();
        request.setUsername("username 0");
        request.setPassword("asdf");

        MockHttpServletRequestBuilder builder = post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @LoadTestCase
    @DisplayName("[비정상 작동] Post /api/auth/login - 잘못된 비밀 번호")
    @Test
    void runAbnormally_login_wrongPassword() throws Exception {
        // given
        LoginRequest request = new LoginRequest();
        request.setUsername("username 1");
        request.setPassword("wrong password");

        MockHttpServletRequestBuilder builder = post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}