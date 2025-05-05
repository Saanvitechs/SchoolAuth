package com.erp.school.library;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {
    List<BookIssue> findByBookId(Long bookId);
}