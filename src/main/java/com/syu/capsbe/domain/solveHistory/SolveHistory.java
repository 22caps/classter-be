package com.syu.capsbe.domain.solveHistory;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.problem.Problem;
import com.syu.capsbe.domain.problem.ProblemType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Long solveCount;

    @OneToMany(mappedBy = "solveHistory")
    private List<SolveHistoryDetail> solveHistoryDetail = new ArrayList<>();

    private ProblemType problemType;

    private LocalDateTime solveDate;

    public void addSolveHistoryDetail(SolveHistoryDetail solveHistoryDetail) {
        this.solveHistoryDetail.add(solveHistoryDetail);
        solveHistoryDetail.setSolveHistory(this);
    }

    public SolveHistory(Member member,Long solveCount ,ProblemType problemType, LocalDateTime solveDate, List<SolveHistoryDetail> solveHistoryDetail) {
        this.member = member;
        this.solveCount = solveCount;
        this.problemType = problemType;
        this.solveDate = solveDate;
        this.solveHistoryDetail = solveHistoryDetail;
    }
}
