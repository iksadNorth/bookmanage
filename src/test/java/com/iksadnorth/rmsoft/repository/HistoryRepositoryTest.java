package com.iksadnorth.rmsoft.repository;

import com.iksadnorth.rmsoft.domain.History;
import com.iksadnorth.rmsoft.utils.LoadTestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class HistoryRepositoryTest {
    @Autowired
    private HistoryRepository historyRepository;

    @LoadTestCase
    @Test
    void runNormally_save() {
        // given
        History history = History.borrow(2L, 1L);

        // when
        historyRepository.save(history);

        // then
        List<History> historyList = historyRepository.findAllOrderedByCreatedAtDesc();
        assertEquals(historyList.size(), 5);
        historyList.forEach(entity -> log.info(entity.toString()));
    }

    @LoadTestCase
    @Test
    void runNormally_findAllOrderedByCreatedAtDesc() {
        // given

        // when
        List<History> historyList = historyRepository.findAllOrderedByCreatedAtDesc();

        // then
        assertEquals(historyList.size(), 4);
        historyList.forEach(entity -> log.info(entity.toString()));
    }

    @LoadTestCase
    @Test
    void runNormally_findLatestHistoryByBookId() {
        // given
        long bookId = 2L;

        // when
        History history = historyRepository.findLatestHistoryByBookId(bookId).orElseThrow();

        // then
        assertEquals(history.getId(), 4);
        log.info(history.toString());
    }
}