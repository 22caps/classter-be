package com.syu.capsbe.domain.solveHistory;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "solve_history_details")
public class SolveHistoryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solve_history_id", nullable = false)
    private SolveHistory solveHistory;

    private String question;

    private String answer;

    private String userAnswer;

    private boolean isCorrect;

    @Builder
    public SolveHistoryDetail(SolveHistory solveHistory, String question, String answer,
            String userAnswer, boolean isCorrect) {
        this.solveHistory = solveHistory;
        this.question = question;
        this.answer = answer;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
    }
}
