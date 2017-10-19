package com.sivalabs.jblogger.web.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sivalabs.jblogger.config.JBloggerConfig;
import com.sivalabs.jblogger.domain.PostsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.sivalabs.jblogger.entities.Comment;
import com.sivalabs.jblogger.entities.PageView;
import com.sivalabs.jblogger.entities.Post;
import com.sivalabs.jblogger.services.PostService;

/**
 * @author Siva
 *
 */
@Controller
public class BlogController extends BaseController
{
	private String viewsDir = "blog/";
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private JBloggerConfig jBloggerConfig;
	
	@GetMapping({"","/page/{page}"})
	public String viewPosts(@PathVariable(value="page", required = false) Integer page,
							Model model) {
		if(page == null || page < 1) page = 1;
		PageRequest pageRequest = PageRequest.of(page - 1, jBloggerConfig.getPostsPerPage());
		Page<Post> postsPage = postService.findPosts(pageRequest);
		final PostsResponse postsResponse = this.getPostsResponse(postsPage);
		model.addAttribute("postsResponse",postsResponse);
		model.addAttribute("paginationRootUrl","page");
        return viewsDir+"posts";
	}

	private PostsResponse getPostsResponse(Page<Post> postsPage){
		PostsResponse postsResponse = new PostsResponse();
		postsResponse.setCurrentPageNo(postsPage.getNumber()+1);
		postsResponse.setPosts(postsPage.getContent());
		postsResponse.setTotalPages(postsPage.getTotalPages());
		postsResponse.setTotalPosts(postsPage.getNumberOfElements());
		postsResponse.setHasNextPage(postsPage.hasNext());
		postsResponse.setHasPreviousPage(postsPage.hasPrevious());
		return postsResponse;
	}
	
	@GetMapping("/search")
	public String searchPosts(@RequestParam(value="query", defaultValue="") String query,
							Model model) {
		List<Post> postsResponse = postService.searchPosts(query);
		model.addAttribute("postsResponse",postsResponse);
        return viewsDir+"post_search_results";
	}
	
	@GetMapping({"/tags/{tag}","/tags/{tag}/page/{page}"})
	public String viewPostsByTag(@PathVariable("tag")String tag,
								 @PathVariable(value="page", required = false) Integer page,
								Model model) {
		if(page == null || page < 1) page = 1;
		PageRequest pageRequest = PageRequest.of(page-1, jBloggerConfig.getPostsPerPage());
		Page<Post> postsPage = postService.findPostsByTag(tag, pageRequest);
		final PostsResponse postsResponse = this.getPostsResponse(postsPage);
		model.addAttribute("postsResponse",postsResponse);
		model.addAttribute("paginationRootUrl","tags/"+tag+"/page");
        return viewsDir+"posts";
	}

    @GetMapping("/{postUrl}")
    public String showPost(@PathVariable(value="postUrl") String postUrl, Model model, 
    						HttpServletRequest request,
    						HttpServletResponse response) throws IOException {
        Post post = postService.findPostByUrl(postUrl);
        if(post == null)
        {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
        	return null;
        }
        model.addAttribute("post",post);
        model.addAttribute("comment",new Comment());
        
        this.savePageView(request, post);
        return viewsDir+"viewpost";
    }

	@PostMapping(value="/{postUrl}/comments")
	public String addComment(@PathVariable(value="postUrl") String postUrl, 
							@Valid @ModelAttribute("comment") Comment comment, 
							BindingResult result, Model model) {
		Post post = postService.findPostByUrl(postUrl);
		if(result.hasErrors()){
	        model.addAttribute("post",post);
	        model.addAttribute("comment",comment);
			return viewsDir+"viewpost";
		}
		comment.setPost(post);
		postService.createComment(comment );
		String subject = "A new comment on post :"+post.getTitle();
		String content = "Comment :\n"+comment.getContent();
		emailService.send(subject, content);
		return "redirect:/"+post.getUrl();
	}

	private void savePageView(HttpServletRequest request, Post post)
	{
		String url = request.getRequestURI();
        String referrer = request.getHeader("referer");
		PageView pageView = new PageView();
		pageView.setReferrer(referrer);
        pageView.setUrl(url);
        pageView.setPost(post);
        pageView.setVisitTime(new Date());
        
		Long viewCount = post.getViewCount();
		if(viewCount == null) viewCount = 0L;
		viewCount++;
		postService.updateViewCount(post.getId(), viewCount);
		
		postService.savePageView(pageView);
		
	}
}
