package com.sivalabs.jblogger.services;

import java.util.List;
import java.util.Optional;

import com.sivalabs.jblogger.config.JBloggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.jblogger.entities.Comment;
import com.sivalabs.jblogger.entities.PageView;
import com.sivalabs.jblogger.entities.Post;
import com.sivalabs.jblogger.repositories.CommentRepository;
import com.sivalabs.jblogger.repositories.PageViewRepository;
import com.sivalabs.jblogger.repositories.PostRepository;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class PostService
{
	private static final String CREATED_ON = "createdOn";

	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final PageViewRepository pageViewRepository;
	private final JBloggerConfig jBloggerConfig;

	@Autowired
	public PostService(PostRepository postRepository,
					   CommentRepository commentRepository,
					   PageViewRepository pageViewRepository,
					   JBloggerConfig jBloggerConfig) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
		this.pageViewRepository = pageViewRepository;
		this.jBloggerConfig = jBloggerConfig;
	}

	public List<Post> findAllPosts()
	{
		Sort sort = new Sort(Direction.DESC, CREATED_ON);
		return postRepository.findAll(sort);
	}

	public Page<Post> findPosts(int pageNo)
	{
		Pageable pageable = getPageRequest(pageNo);
		return postRepository.findAll(pageable);
	}

	public Page<Post> findPostsByTag(String tag, int pageNo)
	{
		Pageable pageable = getPageRequest(pageNo);
		return postRepository.findByTags(tag, pageable);
	}

	private Pageable getPageRequest(int pageNo) {
		Sort sort = new Sort(Direction.DESC, CREATED_ON);
		int pageSize = jBloggerConfig.getPostsPerPage();
		if(pageNo < 1){
			pageNo = 1;
		}
		return PageRequest.of(pageNo-1, pageSize, sort);
	}

	public Optional<Post> findPostById(int postId) {
		return postRepository.findById(postId);
	}

	public Optional<Post> findPostByUrl(String url)
	{
		return postRepository.findByUrl(url);
	}

	public Post createPost(Post post) {
		return postRepository.save(post);
	}

	public Post updatePost(Post post) {
		return postRepository.save(post);
	}

	public void deletePost(Integer postId)
	{
		postRepository.deleteById(postId);
	}

	public List<Post> searchPosts(String query) {
		return postRepository.searchPosts("%"+query.toLowerCase()+"%");
	}

	public void updateViewCount(Integer postId, Long viewCount) {
		postRepository.updateViewCount(postId, viewCount);
	}

	public void savePageView(PageView pageView)
	{
		pageViewRepository.save(pageView);
	}

	public List<Comment> findAllComments()
	{
		Sort sort = new Sort(Direction.DESC, CREATED_ON);
		return commentRepository.findAll(sort);
	}

	public Comment createComment(Comment comment) {
		return commentRepository.save(comment);
	}

	public void deleteComment(Integer commentId)
	{
		commentRepository.deleteById(commentId);
	}

}
