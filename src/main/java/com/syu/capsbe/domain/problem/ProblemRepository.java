package com.syu.capsbe.domain.problem;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    @Query("SELECT p FROM Problem p ORDER BY RAND() LIMIT 1")
    Optional<Problem> getRandomProblem();

    @Query("SELECT p FROM Problem p WHERE p.problemType = :problemType ORDER BY RAND() LIMIT 1")
    Optional<Problem> getProblemByProblemType(ProblemType problemType);

    Optional<Problem> findProblemById(Long problemId);
}
