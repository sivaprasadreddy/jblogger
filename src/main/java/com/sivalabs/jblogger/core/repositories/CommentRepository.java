package com.sivalabs.jblogger.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jblogger.core.entities.Comment;
import org.springframework.stereotype.Repository;

/**
 * @author Siva
 * 
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>
{

}
