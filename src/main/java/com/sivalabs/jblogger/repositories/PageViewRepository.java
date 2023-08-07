package com.sivalabs.jblogger.repositories;

import com.sivalabs.jblogger.entities.PageView;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PageViewRepository extends JpaRepository<PageView, Long> {
    Long countByVisitTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<PageView> findByVisitTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Modifying
    @Query("delete from PageView p where p.post.id = ?1")
    void deleteByPostId(Long postId);
}
