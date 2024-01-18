package com.iksadnorth.bookmanage.service;

import com.iksadnorth.bookmanage.dto.request.BookCreateRequest;
import com.iksadnorth.bookmanage.dto.request.BookUpdateRequest;
import com.iksadnorth.bookmanage.dto.response.BookResponse;
import com.iksadnorth.bookmanage.dto.response.MessageResponse;
import com.iksadnorth.bookmanage.exception.BookException;

import java.util.List;

public interface BookService {
    MessageResponse create(BookCreateRequest request) throws BookException;

    MessageResponse update(Long bookId, BookUpdateRequest request) throws BookException;

    List<BookResponse> read();
}
