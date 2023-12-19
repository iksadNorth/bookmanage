package com.iksadnorth.rmsoft.dto.response;

import com.iksadnorth.rmsoft.domain.History;
import com.iksadnorth.rmsoft.type.HistoryStateType;
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
