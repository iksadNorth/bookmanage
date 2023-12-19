package com.iksadnorth.rmsoft.repository;

import com.iksadnorth.rmsoft.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface MemberRepository {
    void save(Member member);

    Optional<Member> findByUsername(String username);
}
