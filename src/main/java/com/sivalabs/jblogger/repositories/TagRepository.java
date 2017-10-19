package com.sivalabs.jblogger.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sivalabs.jblogger.entities.Tag;

/**
 * @author Siva
 * 
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer>
{

	List<Tag> findByLabelLike(String query);

	Optional<Tag> findByLabel(String trim);
	
	@Query("SELECT t.id, t.label, COUNT(post_id) as count FROM Tag t JOIN t.posts p GROUP BY t.id")
	List<Object[]> getTagsWithCount();

}
