package com.iksadnorth.rmsoft.repository;

import com.iksadnorth.rmsoft.domain.Book;
import com.iksadnorth.rmsoft.utils.LoadTestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @LoadTestCase
    @Test
    void runNormally_save() {
        // given
        Book book = new Book();
        book.setIsbn("1342535");
        book.setTitle("title 1");
        book.setAuthor("author 1");
        book.setPublisher("publisher 1");

        // when
        bookRepository.save(book);

        // then
        Optional<Book> bookOptional = bookRepository.findByIsbn("1342535");
        assertTrue(bookOptional.isPresent());
        log.info(bookOptional.get().toString());
    }

    @LoadTestCase
    @Test
    void runNormally_update() {
        // given
        Long id = 2L;
        String authorChanged = "changed author";

        Book bookInDb = bookRepository.findById(id).orElseThrow();
        bookInDb.setAuthor(authorChanged);

        // when
        bookRepository.update(bookInDb);

        // then
        Book bookChanged = bookRepository.findById(id).orElseThrow();
        assertEquals(authorChanged, bookChanged.getAuthor());
    }

    @LoadTestCase
    @Test
    void runNormally_findByIsbn() {
        // given
        String isbn = "123456789";

        // when
        Optional<Book> memberOptional = bookRepository.findByIsbn(isbn);

        // then
        assertTrue(memberOptional.isPresent());
        log.info(memberOptional.get().toString());
    }

    @LoadTestCase
    @Test
    void runNormally_findById() {
        // given
        Long id = 2L;

        // when
        Optional<Book> memberOptional = bookRepository.findById(id);

        // then
        assertTrue(memberOptional.isPresent());
        log.info(memberOptional.get().toString());
    }

}