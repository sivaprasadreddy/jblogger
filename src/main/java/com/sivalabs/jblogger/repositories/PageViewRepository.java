package com.sivalabs.jblogger.repositories;

import java.util.Date;
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
	//@Query("select count(pv) from PageView pv where pv.visitTime between ?1 and ?2")
	Long countByVisitTimeBetween(Date startDate, Date endDate);

	List<PageView> findByVisitTimeBetween(Date startDate, Date endDate);

}
