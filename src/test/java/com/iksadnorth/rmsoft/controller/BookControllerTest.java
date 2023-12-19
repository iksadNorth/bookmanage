package com.iksadnorth.rmsoft.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iksadnorth.rmsoft.dto.request.BookCreateRequest;
import com.iksadnorth.rmsoft.dto.request.BookUpdateRequest;
import com.iksadnorth.rmsoft.jwt.JwtUtil;
import com.iksadnorth.rmsoft.utils.LoadTestCase;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class BookControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private JwtUtil jwtUtil;

    @LoadTestCase
    @DisplayName("[정상 작동] Post /api/books")
    @Test
    void runNormally_create() throws Exception {
        // given
        String jwt = jwtUtil.createToken("username 4");

        BookCreateRequest request = new BookCreateRequest();
        request.setTitle("이것이 테스트다.");
        request.setAuthor("test author");
        request.setPublisher("test publisher");
        request.setIsbn("123");

        MockHttpServletRequestBuilder builder = post("/api/books")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @LoadTestCase
    @DisplayName("[비정상 작동] Post /api/books - 관리자가 아닌 유저가 접근")
    @Test
    void runAbnormally_create_notAdmin() throws Exception {
        // given
        String jwt = jwtUtil.createToken("username 1");

        BookCreateRequest request = new BookCreateRequest();
        request.setTitle("이것이 테스트다.");
        request.setAuthor("test author");
        request.setPublisher("test publisher");
        request.setIsbn("123");

        MockHttpServletRequestBuilder builder = post("/api/books")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @LoadTestCase
    @DisplayName("[비정상 작동] Post /api/books - 중복되는 isbn")
    @Test
    void runAbnormally_create_duplicatedIsbn() throws Exception {
        // given
        String jwt = jwtUtil.createToken("username 4");

        BookCreateRequest request = new BookCreateRequest();
        request.setTitle("이것이 테스트다.");
        request.setAuthor("test author");
        request.setPublisher("test publisher");
        request.setIsbn("11111");

        MockHttpServletRequestBuilder builder = post("/api/books")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @LoadTestCase
    @DisplayName("[정상 작동] Put /api/books/1")
    @Test
    void runNormally_update() throws Exception {
        // given
        String jwt = jwtUtil.createToken("username 4");

        BookUpdateRequest request = new BookUpdateRequest();
        request.setTitle("이것이 테스트다.");
        request.setAuthor("test author");
        request.setPublisher("test publisher");
        request.setIsbn("123");

        MockHttpServletRequestBuilder builder = put("/api/books/1")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @LoadTestCase
    @DisplayName("[비정상 작동] Put /api/books/1 - 관리자가 아닌 유저가 접근")
    @Test
    void runAbnormally_update_notAdmin() throws Exception {
        // given
        String jwt = jwtUtil.createToken("username 1");

        BookUpdateRequest request = new BookUpdateRequest();
        request.setTitle("이것이 테스트다.");
        request.setAuthor("test author");
        request.setPublisher("test publisher");
        request.setIsbn("123");

        MockHttpServletRequestBuilder builder = put("/api/books/1")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @LoadTestCase
    @DisplayName("[비정상 작동] Put /api/books/1 - 중복되는 isbn")
    @Test
    void runAbnormally_update_duplicatedIsbn() throws Exception {
        // given
        String jwt = jwtUtil.createToken("username 4");

        BookUpdateRequest request = new BookUpdateRequest();
        request.setTitle("이것이 테스트다.");
        request.setAuthor("test author");
        request.setPublisher("test publisher");
        request.setIsbn("11111");

        MockHttpServletRequestBuilder builder = put("/api/books/1")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isConflict());
    }
}