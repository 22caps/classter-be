package com.syu.capsbe.domain.solveHistory;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.problem.ProblemType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "plugin_solve_histories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PluginSolveHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(value = EnumType.STRING)
    private ProblemType problemType;

    private boolean isCorrect;

    @Builder
    public PluginSolveHistory(Member member, ProblemType problemType, boolean isCorrect) {
        this.member = member;
        this.problemType = problemType;
        this.isCorrect = isCorrect;
    }
}
