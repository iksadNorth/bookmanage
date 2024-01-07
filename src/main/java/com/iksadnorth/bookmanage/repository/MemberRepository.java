package com.iksadnorth.bookmanage.repository;

import com.iksadnorth.bookmanage.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface MemberRepository {
    void save(Member member);

    Optional<Member> findByUsername(String username);
}
