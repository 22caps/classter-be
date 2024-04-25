package com.syu.capsbe.domain.problem;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    @Query("SELECT p FROM Problem p WHERE p.problemType = :problemType ORDER BY RAND() LIMIT :problemCount")
    List<Problem> getProblemTypeIsWord(ProblemType problemType, int problemCount);
}
