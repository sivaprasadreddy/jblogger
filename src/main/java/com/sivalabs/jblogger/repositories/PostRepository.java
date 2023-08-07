package com.sivalabs.jblogger.repositories;

import com.sivalabs.jblogger.entities.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p INNER JOIN p.tags t WHERE t.label= :tag")
    Page<Post> findByTags(@Param("tag") String tag, Pageable pageable);

    Optional<Post> findByUrl(String url);

    @Modifying
    @Query("update Post p set p.viewCount=:viewCount where p.id=:postId")
    void updateViewCount(@Param("postId") Long postId, @Param("viewCount") Long count);

    @Query("select sum(p.viewCount) from Post p")
    Long getTotalPostViewCount();

    @Query("SELECT p FROM Post p WHERE LOWER(p.title) like ?1 or LOWER(p.content) like ?1")
    List<Post> searchPosts(String query);
}
