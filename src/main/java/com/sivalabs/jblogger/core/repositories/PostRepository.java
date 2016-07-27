package com.sivalabs.jblogger.core.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sivalabs.jblogger.core.entities.Post;
import org.springframework.stereotype.Repository;

/**
 * @author Siva
 * 
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>
{
	@Query("SELECT p FROM Post p INNER JOIN p.tags t WHERE t.label= :tag")
	Page<Post> findByTags(@Param("tag") String tag, Pageable pageable);

	//@Query("SELECT distinct p FROM Post p LEFT JOIN FETCH p.createdBy LEFT JOIN FETCH p.tags LEFT JOIN FETCH p.comments where p.url=?1")
	Post findByUrl(String url);

	@Modifying
	@Query("update Post p set p.viewCount=:viewCount where p.id=:postId")
	void updateViewCount(@Param("postId") Integer postId, @Param("viewCount") Long count);

	@Query("select sum(p.viewCount) from Post p")
	Long getTotalPostViewCount();

	@Query("SELECT p FROM Post p WHERE p.title like ?1 or p.content like ?1")
	List<Post> searchPosts(String query);

	
}
	