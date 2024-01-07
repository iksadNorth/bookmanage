package com.iksadnorth.bookmanage.dto.response;

import com.iksadnorth.bookmanage.domain.History;
import com.iksadnorth.bookmanage.type.HistoryStateType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter @RequiredArgsConstructor
public class HistoryResponse {
    private final LocalDateTime createdAt;
    private final HistoryStateType type;

    public static HistoryResponse fromEntity(History history) {
        return new HistoryResponse(
                history.getCreatedAt(),
                history.getType()
        );
    }
}
