package com.iksadnorth.bookmanage.controller;

import com.iksadnorth.bookmanage.dto.request.HistoryCreateRequest;
import com.iksadnorth.bookmanage.dto.response.HistoryResponse;
import com.iksadnorth.bookmanage.dto.response.MessageResponse;
import com.iksadnorth.bookmanage.exception.BookException;
import com.iksadnorth.bookmanage.security.UserDetailsImpl;
import com.iksadnorth.bookmanage.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/api/books/{bookId}/histories")
    public List<HistoryResponse> readHistories(
            @PathVariable Long bookId
    ) {
        return historyService.readHistories(bookId);
    }

    @PostMapping("/api/books/{bookId}/histories")
    public MessageResponse createHistories(
            @PathVariable Long bookId,
            @AuthenticationPrincipal UserDetailsImpl principal,
            @RequestBody HistoryCreateRequest request
            ) throws BookException {
        return historyService.createHistories(bookId, principal.getMember(), request.getType());
    }
}
