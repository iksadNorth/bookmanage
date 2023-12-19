package com.iksadnorth.rmsoft.dto.request;

import com.iksadnorth.rmsoft.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class BookCreateRequest {
    private String title;
    private String author;
    private String publisher;
    private String isbn;

    public Book toEntity() {
        Book book = new Book();
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setPublisher(this.publisher);
        book.setIsbn(this.isbn);
        return book;
    }
}
