package com.sivalabs.jblogger.services;

import com.sivalabs.jblogger.config.ApplicationProperties;
import com.sivalabs.jblogger.domain.PostDTO;
import com.sivalabs.jblogger.domain.PostsResponse;
import com.sivalabs.jblogger.entities.Comment;
import com.sivalabs.jblogger.entities.PageView;
import com.sivalabs.jblogger.entities.Post;
import com.sivalabs.jblogger.mappers.PostsResponseMapper;
import com.sivalabs.jblogger.repositories.CommentRepository;
import com.sivalabs.jblogger.repositories.PageViewRepository;
import com.sivalabs.jblogger.repositories.PostRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {
    private static final String CREATED_ON = "createdOn";

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PageViewRepository pageViewRepository;
    private final PostsResponseMapper postsResponseMapper;
    private final ApplicationProperties applicationProperties;

    @Autowired
    public PostService(
            PostRepository postRepository,
            CommentRepository commentRepository,
            PageViewRepository pageViewRepository,
            PostsResponseMapper postsResponseMapper,
            ApplicationProperties applicationProperties) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.pageViewRepository = pageViewRepository;
        this.postsResponseMapper = postsResponseMapper;
        this.applicationProperties = applicationProperties;
    }

    public List<PostDTO> findAllPosts() {
        Sort sort = Sort.by(Direction.DESC, CREATED_ON);
        List<Post> postList = postRepository.findAll(sort);
        return this.postsResponseMapper.map(postList);
    }

    public PostsResponse findPosts(Integer pageNo) {
        Pageable pageable = this.getPageRequest(pageNo);
        Page<Post> postsPage = postRepository.findAll(pageable);
        return this.postsResponseMapper.map(postsPage);
    }

    public PostsResponse findPostsByTag(String tag, Integer pageNo) {
        Pageable pageable = getPageRequest(pageNo);
        Page<Post> postsPage = postRepository.findByTags(tag, pageable);
        return this.postsResponseMapper.map(postsPage);
    }

    private Pageable getPageRequest(Integer pageNo) {
        Sort sort = Sort.by(Direction.DESC, CREATED_ON);
        int pageSize = applicationProperties.getPostsPerPage();
        if (pageNo == null || pageNo < 1) pageNo = 1;
        return PageRequest.of(pageNo - 1, pageSize, sort);
    }

    public Optional<Post> findPostById(Long postId) {
        return postRepository.findById(postId);
    }

    public Optional<PostDTO> findPostByUrl(String url) {
        return postRepository.findByUrl(url).map(this.postsResponseMapper::map);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        pageViewRepository.deleteByPostId(postId);
        postRepository.deleteById(postId);
    }

    public List<PostDTO> searchPosts(String query) {
        return this.postsResponseMapper.map(postRepository.searchPosts("%" + query.toLowerCase() + "%"));
    }

    public void updateViewCount(Long postId, Long viewCount) {
        postRepository.updateViewCount(postId, viewCount);
    }

    public void savePageView(PageView pageView) {
        pageViewRepository.save(pageView);
    }

    public List<Comment> findAllComments() {
        Sort sort = Sort.by(Direction.DESC, CREATED_ON);
        return commentRepository.findAll(sort);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
