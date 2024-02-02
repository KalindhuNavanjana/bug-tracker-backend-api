package io.entgra.bugtrackerapi.repository;

import io.entgra.bugtrackerapi.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    int countByStatus(String status);
}
