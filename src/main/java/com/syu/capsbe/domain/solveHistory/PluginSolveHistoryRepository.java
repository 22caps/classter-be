package com.syu.capsbe.domain.solveHistory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PluginSolveHistoryRepository extends JpaRepository<PluginSolveHistory, Long> {


    // member_id로 조회
    @Query("SELECT sh FROM PluginSolveHistory sh WHERE sh.member.id = :memberId AND sh.problemType = 'GRAMMAR'")
    List<PluginSolveHistory> findGrammarByMemberId(Long memberId);

    @Query("SELECT sh FROM PluginSolveHistory sh WHERE sh.member.id = :memberId AND sh.problemType = 'CONVERSATION'")
    List<PluginSolveHistory> findConversationByMemberId(Long memberId);

    @Query("SELECT sh FROM PluginSolveHistory sh WHERE sh.member.id = :memberId AND sh.problemType = 'WORD'")
    List<PluginSolveHistory> findWordByMemberId(Long memberId);
}
