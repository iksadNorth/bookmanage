package com.iksadnorth.bookmanage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iksadnorth.bookmanage.dto.request.HistoryCreateRequest;
import com.iksadnorth.bookmanage.jwt.JwtUtil;
import com.iksadnorth.bookmanage.type.HistoryChangeType;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class HistoryControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private JwtUtil jwtUtil;

    @LoadTestCase
    @DisplayName("[정상 작동] Get /api/books/1/histories")
    @Test
    void runNormally_readHistories() throws Exception {
        // given
        MockHttpServletRequestBuilder builder = get("/api/books/1/histories");

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @LoadTestCase
    @DisplayName("[정상 작동] Post /api/books/3/histories - 대출")
    @Test
    void runNormally_createHistories_loan() throws Exception {
        // given
        HistoryChangeType type = HistoryChangeType.LOAN;
        HistoryCreateRequest request = new HistoryCreateRequest();
        request.setType(type);
        String jwt = jwtUtil.createToken("username 3");

        MockHttpServletRequestBuilder builder = post("/api/books/3/histories")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @LoadTestCase
    @DisplayName("[정상 작동] Post /api/books/1/histories - 반납")
    @Test
    void runNormally_createHistories_return() throws Exception {
        // given
        HistoryChangeType type = HistoryChangeType.RETURN;
        HistoryCreateRequest request = new HistoryCreateRequest();
        request.setType(type);
        String jwt = jwtUtil.createToken("username 2");

        MockHttpServletRequestBuilder builder = post("/api/books/1/histories")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @LoadTestCase
    @DisplayName("[비정상 작동] Post /api/books/1/histories - 존재하지 않는 상태 업데이트 요구")
    @Test
    void runAbnormally_createHistories_nonExistedState() throws Exception {
        // given
        String type = "wrong type";
        String jwt = jwtUtil.createToken("username 1");

        MockHttpServletRequestBuilder builder = post("/api/books/1/histories")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(type));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @LoadTestCase
    @DisplayName("[비정상 작동] Post /api/books/1/histories - 대출 중인데 또 대출요구")
    @Test
    void runAbnormally_createHistories_requestEmptyBook() throws Exception {
        // given
        HistoryChangeType type = HistoryChangeType.LOAN;
        HistoryCreateRequest request = new HistoryCreateRequest();
        request.setType(type);
        String jwt = jwtUtil.createToken("username 1");

        MockHttpServletRequestBuilder builder = post("/api/books/1/histories")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @LoadTestCase
    @DisplayName("[비정상 작동] Post /api/books/3/histories - 대출한 사람이 아닌 사람이 반납")
    @Test
    void runAbnormally_createHistories_returnByOther() throws Exception {
        // given
        HistoryChangeType type = HistoryChangeType.RETURN;
        HistoryCreateRequest request = new HistoryCreateRequest();
        request.setType(type);
        String jwt = jwtUtil.createToken("username 1");

        MockHttpServletRequestBuilder builder = post("/api/books/1/histories")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request));

        // when & then
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isConflict());
    }
}