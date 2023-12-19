package com.iksadnorth.rmsoft.domain;

import com.iksadnorth.rmsoft.type.HistoryStateType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter @Setter @NoArgsConstructor
public class History {
    private Long id;
    private LocalDateTime createdAt;
    private HistoryStateType type;
    private Long bookId;
    private Long memberId;

    public static History borrow(Long bookId, Long memberId) {
        History history = new History();
        history.setCreatedAt(LocalDateTime.now());
        history.setType(HistoryStateType.BORROWING);
        history.setBookId(bookId);
        history.setMemberId(memberId);
        return history;
    }

    public static History checkIn(Long bookId, Long memberId) {
        History history = new History();
        history.setCreatedAt(LocalDateTime.now());
        history.setType(HistoryStateType.RETURN_COMPLETED);
        history.setBookId(bookId);
        history.setMemberId(memberId);
        return history;
    }
}
