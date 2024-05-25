package com.syu.capsbe.domain.solveHistory;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.problem.ProblemType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "solve_histories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SolveHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "solveHistory")
    private List<SolveHistoryDetail> solveHistoryDetails = new ArrayList<>();

    private ProblemType problemType;

    private LocalDateTime solveDate;

    private double correctRate;

    private boolean isCompleted;

    private boolean isReviewed;

    @Builder
    public SolveHistory(Member member, ProblemType problemType, LocalDateTime solveDate) {
        this.member = member;
        this.problemType = problemType;
        this.solveDate = solveDate;
        this.correctRate = 0;
    }

    public void updateCorrectRate(double correctRate) {
        this.correctRate = Math.round(correctRate);
    }

    public void completeSolveHistory() {
        this.isCompleted = true;
    }

    public void reviewSolveHistory() {
        this.isReviewed = true;
    }

    public int getSolveHistoryDetailsSize() {
        return this.solveHistoryDetails.size();
    }
}
