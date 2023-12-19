package com.iksadnorth.rmsoft.service;

import com.iksadnorth.rmsoft.dto.request.BookCreateRequest;
import com.iksadnorth.rmsoft.dto.request.BookUpdateRequest;
import com.iksadnorth.rmsoft.dto.response.MessageResponse;
import com.iksadnorth.rmsoft.exception.BookException;

public interface BookService {
    MessageResponse create(BookCreateRequest request) throws BookException;

    MessageResponse update(Long bookId, BookUpdateRequest request) throws BookException;
}
