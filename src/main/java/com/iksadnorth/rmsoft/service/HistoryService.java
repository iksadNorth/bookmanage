package com.iksadnorth.rmsoft.service;

import com.iksadnorth.rmsoft.domain.Member;
import com.iksadnorth.rmsoft.dto.response.HistoryResponse;
import com.iksadnorth.rmsoft.dto.response.MessageResponse;
import com.iksadnorth.rmsoft.exception.BookException;
import com.iksadnorth.rmsoft.type.HistoryChangeType;

import java.util.List;

public interface HistoryService {
    List<HistoryResponse> readHistories(Long bookId);

    MessageResponse createHistories(Long bookId, Member memberLoggedIn, HistoryChangeType type) throws BookException;
}
