package com.syu.capsbe.domain.solveHistory;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SolveHistoryRepository extends JpaRepository<SolveHistory, Long> {

    @Query("SELECT sh FROM SolveHistory sh WHERE sh.member.id = :memberId")
    List<SolveHistory> findByMemberId(Long memberId);

    @Query("SELECT sh FROM SolveHistory sh WHERE sh.member.id = :memberId AND sh.solveCount = :solveCount")
    Optional<SolveHistory> findByMemberIdAndSolveHistoryId(Long memberId, Long solveCount);
}
