package com.iksadnorth.bookmanage.dto.response;

import com.iksadnorth.bookmanage.domain.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.isbn = book.getIsbn();
    }
}
