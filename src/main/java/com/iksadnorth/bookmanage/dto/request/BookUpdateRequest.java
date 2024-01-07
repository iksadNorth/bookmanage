package com.iksadnorth.bookmanage.dto.request;

import com.iksadnorth.bookmanage.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class BookUpdateRequest {
    private String title;
    private String author;
    private String publisher;
    private String isbn;

    public void overwriteTo(Book book) {
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setPublisher(this.publisher);
        book.setIsbn(this.isbn);
    }
}
