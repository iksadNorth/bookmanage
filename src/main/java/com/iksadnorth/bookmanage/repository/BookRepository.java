package com.iksadnorth.bookmanage.repository;

import com.iksadnorth.bookmanage.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface BookRepository {
    void save(Book book);

    void update(Book book);

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findById(Long bookId);

    List<Book> findAll();
}
