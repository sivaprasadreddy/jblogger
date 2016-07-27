package com.sivalabs.jblogger.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jblogger.core.entities.Blog;
import org.springframework.stereotype.Repository;

/**
 * @author Siva
 * 
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>
{

}
