package com.iksadnorth.rmsoft.service;

import com.iksadnorth.rmsoft.domain.History;
import com.iksadnorth.rmsoft.domain.Member;
import com.iksadnorth.rmsoft.dto.response.HistoryResponse;
import com.iksadnorth.rmsoft.dto.response.MessageResponse;
import com.iksadnorth.rmsoft.exception.BookException;
import com.iksadnorth.rmsoft.repository.HistoryRepository;
import com.iksadnorth.rmsoft.type.HistoryChangeType;
import com.iksadnorth.rmsoft.type.HistoryStateType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    @Override
    public List<HistoryResponse> readHistories(Long bookId) {
        // 그동안 대출이력을 출력.
        return historyRepository.findAllOrderedByCreatedAtDesc().stream()
                .map(HistoryResponse::fromEntity)
                .toList();
    }

    @Transactional
    @Override
    public MessageResponse createHistories(Long bookId, Member memberLoggedIn, HistoryChangeType type) throws BookException {
        History entity;
        switch (type) {
            case LOAN -> entity = loanBook(bookId, memberLoggedIn);
            case RETURN -> entity = returnBook(bookId, memberLoggedIn);
            default -> throw new BookException(
                    "해당 type은 존재하지 않습니다. 올바른 type으로 다시 기입해주세요.",
                    HttpStatus.BAD_REQUEST
            );
        }

        // 기록 남기기.
        historyRepository.save(entity);
        return new MessageResponse(
                "성공적으로 대출 이력을 갱신했습니다."
        );
    }

    private History loanBook(Long bookId, Member memberLoggedIn) throws BookException {
        Optional<History> historyOptionalInDb = historyRepository.findLatestHistoryByBookId(bookId);

        // type이 '대출'일 경우, 가장 최근 대출 기록이 대출 중이면 안됨.
        // 대출 내역이 아예 없다면, 결과값 출력.
        Optional<HistoryStateType> historyStateBorrowing = historyOptionalInDb
                .map(History::getType)
                .filter(HistoryStateType.BORROWING::equals);

        if (historyStateBorrowing.isPresent()) {
            throw new BookException(
                    "이미 대출 중인 서적입니다. 해당 서적을 대출할 수 없습니다.",
                    HttpStatus.CONFLICT
            );
        }

        return History.borrow(bookId, memberLoggedIn.getId());
    }

    private History returnBook(Long bookId, Member memberLoggedIn) throws BookException {
        Optional<History> historyOptionalInDb = historyRepository.findLatestHistoryByBookId(bookId);

        // type이 '반납'일 경우, 가장 최근 대출 기록의 주인이 해당 대출 기록의 주체어야 함.
        // 대출 내역이 아예 없다면, 오류 발생.
        Optional<Long> historyMemberIdReturned = historyOptionalInDb
                .map(History::getMemberId)
                .filter(memberLoggedIn.getId()::equals);

        if (historyMemberIdReturned.isEmpty()) {
            throw new BookException(
                    "해당 유저의 해당 서적에 대한 대출 기록이 없습니다.",
                    HttpStatus.CONFLICT
            );
        }

        return History.checkIn(bookId, memberLoggedIn.getId());
    }
}
