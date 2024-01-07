package com.iksadnorth.bookmanage.repository;

import com.iksadnorth.bookmanage.domain.History;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface HistoryRepository {
    void save(History entity);

    List<History> findAllOrderedByCreatedAtDesc();

    Optional<History> findLatestHistoryByBookId(Long bookId);
}
