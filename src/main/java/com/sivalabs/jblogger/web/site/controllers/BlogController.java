/**
 * 
 */
package com.sivalabs.jblogger.web.site.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sivalabs.jblogger.JBloggerConstants;
import com.sivalabs.jblogger.entities.Comment;
import com.sivalabs.jblogger.entities.PageView;
import com.sivalabs.jblogger.entities.Post;
import com.sivalabs.jblogger.services.PostService;

/**
 * @author Siva
 *
 */
@Controller
@RequestMapping(value="posts")
public class BlogController extends BaseController
{
	private String viewsDir = "blog/";
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String viewPosts(@RequestParam(value="page", defaultValue="0") Integer page, 
							Model model) {
		
		PageRequest pageRequest = new PageRequest((page < 0 ) ? 0 : page , JBloggerConstants.POSTS_PER_PAGE);
		Page<Post> postsResponse = postService.findPosts(pageRequest);
		model.addAttribute("postsResponse",postsResponse);
		model.addAttribute("paginationRootUrl","posts");
        return viewsDir+"posts";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String searchPosts(@RequestParam(value="query", defaultValue="") String query,
							Model model) {

		List<Post> postsResponse = postService.searchPosts(query);
		model.addAttribute("postsResponse",postsResponse);
        return viewsDir+"post_search_results";
	}
	
	@RequestMapping(value="/tags/{tag}", method=RequestMethod.GET)
	public String viewPostsByTag(@PathVariable("tag")String tag, 
								@RequestParam(value="page", defaultValue="0") Integer page, 
								Model model) {
		PageRequest pageRequest = new PageRequest((page < 0 ) ? 0 : page, JBloggerConstants.POSTS_PER_PAGE);
		Page<Post> postsResponse = postService.findPostsByTag(tag, pageRequest);
		model.addAttribute("postsResponse",postsResponse);
		model.addAttribute("paginationRootUrl","posts/tags/"+tag);
        return viewsDir+"posts";
	}

	

    @RequestMapping(value="/{postUrl}", method=RequestMethod.GET)
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

	@RequestMapping(value="/{postUrl}/comments", method=RequestMethod.POST)
	public String addComment(@PathVariable(value="postUrl") String postUrl, 
							@Valid @ModelAttribute("comment") Comment comment, 
							BindingResult result, Model model) {
		Post post = postService.findPostByUrl(postUrl);
		if(result.hasErrors()){
	        model.addAttribute("post",post);
	        model.addAttribute("comment",comment);
			return "viewpost";
		}
		comment.setPost(post);
		postService.createComment(comment );
		String subject = "A new comment on post :"+post.getTitle();
		String content = "Comment :\n"+comment.getContent();
		emailService.send(subject, content);
		return "redirect:/posts/"+post.getUrl();
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
