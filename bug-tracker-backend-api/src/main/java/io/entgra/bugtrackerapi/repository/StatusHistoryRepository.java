package io.entgra.bugtrackerapi.repository;

import io.entgra.bugtrackerapi.entity.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory,Long> {
    List<StatusHistory> findByIssueId(Long issueID);
}
