package com.sivalabs.jblogger.web.controllers;

import com.sivalabs.jblogger.domain.PostsResponse;
import com.sivalabs.jblogger.entities.Comment;
import com.sivalabs.jblogger.entities.PageView;
import com.sivalabs.jblogger.entities.Post;
import com.sivalabs.jblogger.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Siva
 *
 */
@Controller
public class BlogController extends BaseController
{
	private static final String viewsDir = "blog/";
	
	private PostService postService;

	@Autowired
	public BlogController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping({"","/page/{page}"})
	public String viewPosts(@PathVariable(value="page", required = false) Integer page,
							Model model) {
		if(page == null || page < 1) page = 1;
		Page<Post> postsPage = postService.findPosts(page);
		final PostsResponse postsResponse = this.getPostsResponse(postsPage);
		model.addAttribute("postsResponse",postsResponse);
		model.addAttribute("paginationRootUrl","page");
        return viewsDir+"posts";
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
		Page<Post> postsPage = postService.findPostsByTag(tag, page);
		final PostsResponse postsResponse = this.getPostsResponse(postsPage);
		model.addAttribute("postsResponse",postsResponse);
		model.addAttribute("paginationRootUrl","tags/"+tag+"/page");
        return viewsDir+"posts";
	}

    @GetMapping("/{postUrl}")
    public String showPost(@PathVariable(value="postUrl") String postUrl, Model model, 
    						HttpServletRequest request,
    						HttpServletResponse response) throws IOException {
        Optional<Post> postObj = postService.findPostByUrl(postUrl);
        if(!postObj.isPresent())
        {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
        	return null;
        }
        model.addAttribute("post",postObj.get());
        model.addAttribute("comment",new Comment());
        
        this.savePageView(request, postObj.get());
        return viewsDir+"viewpost";
    }

	@PostMapping(value="/{postUrl}/comments")
	public String addComment(@PathVariable(value="postUrl") String postUrl, 
							@Valid @ModelAttribute("comment") Comment comment, 
							BindingResult result,
							 Model model,
							HttpServletResponse response) throws IOException {
		Optional<Post> postObj = postService.findPostByUrl(postUrl);
		if(!postObj.isPresent())
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		final Post post = postObj.get();
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

	private void savePageView(HttpServletRequest request, Post post)
	{
		String url = request.getRequestURI();
        String referrer = request.getHeader("referer");
		PageView pageView = new PageView();
		pageView.setReferrer(referrer);
        pageView.setUrl(url);
        pageView.setPost(post);
        pageView.setVisitTime(LocalDateTime.now());
        
		Long viewCount = post.getViewCount();
		if(viewCount == null) viewCount = 0L;
		viewCount++;
		postService.updateViewCount(post.getId(), viewCount);
		
		postService.savePageView(pageView);
		
	}
}
