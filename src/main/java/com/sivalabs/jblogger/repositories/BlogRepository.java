package com.sivalabs.jblogger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sivalabs.jblogger.entities.Blog;

/**
 * @author Siva
 * 
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>
{

}
