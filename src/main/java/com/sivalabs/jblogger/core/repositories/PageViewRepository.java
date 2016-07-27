package com.sivalabs.jblogger.core.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jblogger.core.entities.PageView;
import org.springframework.stereotype.Repository;

/**
 * @author Siva
 * 
 */
@Repository
public interface PageViewRepository extends JpaRepository<PageView, Integer>
{
	//@Query("select count(pv) from PageView pv where pv.visitTime between ?1 and ?2")
	Long countByVisitTimeBetween(Date startDate, Date endDate);

	List<PageView> findByVisitTimeBetween(Date startDate, Date endDate);

}
