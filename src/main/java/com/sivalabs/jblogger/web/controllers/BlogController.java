package com.sivalabs.jblogger.web.controllers;

import com.sivalabs.jblogger.domain.PostDTO;
import com.sivalabs.jblogger.domain.PostsResponse;
import com.sivalabs.jblogger.entities.Comment;
import com.sivalabs.jblogger.entities.PageView;
import com.sivalabs.jblogger.entities.Post;
import com.sivalabs.jblogger.mappers.PostsResponseMapper;
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

@Controller
public class BlogController extends BaseController
{
	private static final String viewsDir = "blog/";
	
	private final PostService postService;

	@Autowired
	public BlogController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping({"","/page/{page}"})
	public String viewPosts(@PathVariable(value="page", required = false) Integer page,
							Model model) {
		final PostsResponse postsResponse = postService.findPosts(page);
		model.addAttribute("postsResponse",postsResponse);
		model.addAttribute("paginationRootUrl","page");
        return viewsDir+"posts";
	}
	
	@GetMapping("/search")
	public String searchPosts(@RequestParam(value="query", defaultValue="") String query,
							Model model) {
		List<PostDTO> postsResponse = postService.searchPosts(query);
		model.addAttribute("postsResponse",postsResponse);
        return viewsDir+"post_search_results";
	}
	
	@GetMapping({"/tags/{tag}","/tags/{tag}/page/{page}"})
	public String viewPostsByTag(@PathVariable("tag")String tag,
								 @PathVariable(value="page", required = false) Integer page,
								Model model) {
		final PostsResponse postsResponse = postService.findPostsByTag(tag, page);
		model.addAttribute("postsResponse",postsResponse);
		model.addAttribute("paginationRootUrl","tags/"+tag+"/page");
        return viewsDir+"posts";
	}

    @GetMapping("/{postUrl}")
    public String showPost(@PathVariable(value="postUrl") String postUrl,
						    Model model,
    						HttpServletRequest request,
    						HttpServletResponse response) throws IOException {
        Optional<PostDTO> postObj = postService.findPostByUrl(postUrl);
        if(!postObj.isPresent())
        {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
        	return null;
        }
        model.addAttribute("post", postObj.get());
        model.addAttribute("comment",new Comment());
        
        this.savePageView(request, postObj.get());
        return viewsDir+"viewpost";
    }

	@PostMapping("/{postUrl}/comments")
	public String addComment(@PathVariable(value="postUrl") String postUrl, 
							@Valid @ModelAttribute("comment") Comment comment, 
							BindingResult result,
							 Model model,
							HttpServletResponse response) throws IOException {
		Optional<PostDTO> postObj = postService.findPostByUrl(postUrl);
		if(!postObj.isPresent())
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		final PostDTO post = postObj.get();
		if(result.hasErrors()){
	        model.addAttribute("post",post);
	        model.addAttribute("comment",comment);
			return viewsDir+"viewpost";
		}
		Post postEntity = new Post();
		postEntity.setId(post.getId());
		comment.setPost(postEntity);
		postService.createComment(comment);
		String subject = "A new comment on post :"+post.getTitle();
		String content = "Comment :\n"+comment.getContent();
		emailService.send(subject, content);
		return "redirect:/"+post.getUrl();
	}

	private void savePageView(HttpServletRequest request, PostDTO post)
	{
		String url = request.getRequestURI();
        String referrer = request.getHeader("referer");
		PageView pageView = new PageView();
		pageView.setReferrer(referrer);
        pageView.setUrl(url);
        Post postEntity = new Post();
        postEntity.setId(post.getId());
        pageView.setPost(postEntity);
        pageView.setVisitTime(LocalDateTime.now());
        
		Long viewCount = post.getViewCount();
		if(viewCount == null) viewCount = 0L;
		viewCount++;
		postService.updateViewCount(post.getId(), viewCount);
		
		postService.savePageView(pageView);
	}
}
