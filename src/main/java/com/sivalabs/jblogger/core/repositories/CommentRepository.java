package com.sivalabs.jblogger.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jblogger.core.entities.Comment;

/**
 * @author Siva
 * 
 */
public interface CommentRepository extends JpaRepository<Comment, Integer>
{

}
