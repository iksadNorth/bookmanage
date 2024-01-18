package com.iksadnorth.bookmanage.controller;

import com.iksadnorth.bookmanage.dto.request.BookCreateRequest;
import com.iksadnorth.bookmanage.dto.request.BookUpdateRequest;
import com.iksadnorth.bookmanage.dto.response.BookResponse;
import com.iksadnorth.bookmanage.dto.response.MessageResponse;
import com.iksadnorth.bookmanage.exception.BookException;
import com.iksadnorth.bookmanage.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/api/books")
    public MessageResponse create(
            @RequestBody BookCreateRequest request
    ) throws BookException {
        return bookService.create(request);
    }

    @GetMapping("/api/books")
    public List<BookResponse> read() {
        return bookService.read();
    }

    @PutMapping("/api/books/{bookId}")
    public MessageResponse update(
            @PathVariable Long bookId,
            @RequestBody BookUpdateRequest request
    ) throws BookException {
        return bookService.update(bookId, request);
    }
}
