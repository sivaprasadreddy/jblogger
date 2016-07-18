package com.sivalabs.jblogger.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jblogger.core.entities.Blog;

/**
 * @author Siva
 * 
 */
public interface BlogRepository extends JpaRepository<Blog, Integer>
{

}
