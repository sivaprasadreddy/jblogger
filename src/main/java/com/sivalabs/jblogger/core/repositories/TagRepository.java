package com.sivalabs.jblogger.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sivalabs.jblogger.core.entities.Tag;

/**
 * @author Siva
 * 
 */
public interface TagRepository extends JpaRepository<Tag, Integer>
{

	List<Tag> findByLabelLike(String query);

	Tag findByLabel(String trim);
	
	@Query("SELECT t.id, t.label, COUNT(post_id) as count FROM Tag t JOIN t.posts p GROUP BY t.id")
	List<Object[]> getTagsWithCount();

}
