package com.syu.capsbe.domain.solveHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PluginSolveHistoryRepository extends JpaRepository<PluginSolveHistory, Long> {

}
