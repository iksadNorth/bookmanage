package com.iksadnorth.bookmanage.service;

import com.iksadnorth.bookmanage.domain.Member;
import com.iksadnorth.bookmanage.dto.response.HistoryResponse;
import com.iksadnorth.bookmanage.dto.response.MessageResponse;
import com.iksadnorth.bookmanage.exception.BookException;
import com.iksadnorth.bookmanage.type.HistoryChangeType;

import java.util.List;

public interface HistoryService {
    List<HistoryResponse> readHistories(Long bookId);

    MessageResponse createHistories(Long bookId, Member memberLoggedIn, HistoryChangeType type) throws BookException;
}
