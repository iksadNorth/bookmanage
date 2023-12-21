package com.iksadnorth.rmsoft.service;

import com.iksadnorth.rmsoft.domain.Book;
import com.iksadnorth.rmsoft.dto.request.BookCreateRequest;
import com.iksadnorth.rmsoft.dto.request.BookUpdateRequest;
import com.iksadnorth.rmsoft.dto.response.MessageResponse;
import com.iksadnorth.rmsoft.exception.BookException;
import com.iksadnorth.rmsoft.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Transactional
    @Override
    public MessageResponse create(BookCreateRequest request) throws BookException {
        // isbn이 중복되면 오류.
        Optional<Book> bookOptional = bookRepository.findByIsbn(request.getIsbn());
        if (bookOptional.isPresent()) {
            throw new BookException(
                    "해당 서적은 이미 존재합니다.",
                    HttpStatus.CONFLICT
            );
        }

        // 도서 데이터 생성
        Book book = request.toEntity();
        bookRepository.save(book);

        return new MessageResponse(
                "성공적으로 도서 등록이 이뤄졌습니다."
        );
    }

    @Transactional
    @Override
    public MessageResponse update(Long bookId, BookUpdateRequest request) throws BookException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("해당 ID의 책은 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        // isbn이 중복되면 오류.
        Optional<Book> bookOptional = bookRepository.findByIsbn(request.getIsbn());
        if (!book.getIsbn().equals(request.getIsbn()) && bookOptional.isPresent()) {
            throw new BookException(
                    "해당 ISBN의 서적은 이미 존재합니다.",
                    HttpStatus.CONFLICT
            );
        }

        // 도서 데이터 수정.
        request.overwriteTo(book);
        bookRepository.update(book);

        return new MessageResponse(
                "성공적으로 도서 수정이 이뤄졌습니다."
        );
    }
}
