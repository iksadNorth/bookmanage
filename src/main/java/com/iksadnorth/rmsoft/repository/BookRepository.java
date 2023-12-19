package com.iksadnorth.rmsoft.repository;

import com.iksadnorth.rmsoft.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface BookRepository {
    void save(Book book);

    void update(Book book);

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findById(Long bookId);
}
