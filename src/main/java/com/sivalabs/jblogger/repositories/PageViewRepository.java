package com.sivalabs.jblogger.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sivalabs.jblogger.entities.PageView;

/**
 * @author Siva
 * 
 */
@Repository
public interface PageViewRepository extends JpaRepository<PageView, Integer>
{
	Long countByVisitTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<PageView> findByVisitTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
