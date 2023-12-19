package com.iksadnorth.rmsoft.controller;

import com.iksadnorth.rmsoft.dto.response.HistoryResponse;
import com.iksadnorth.rmsoft.dto.response.MessageResponse;
import com.iksadnorth.rmsoft.exception.BookException;
import com.iksadnorth.rmsoft.security.UserDetailsImpl;
import com.iksadnorth.rmsoft.service.HistoryService;
import com.iksadnorth.rmsoft.type.HistoryChangeType;
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
            @RequestBody HistoryChangeType type
            ) throws BookException {
        return historyService.createHistories(bookId, principal.getMember(), type);
    }
}
