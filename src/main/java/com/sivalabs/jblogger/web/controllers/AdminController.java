package com.sivalabs.jblogger.web.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.sivalabs.jblogger.domain.BlogOverview;
import com.sivalabs.jblogger.domain.TimePeriod;
import com.sivalabs.jblogger.entities.Post;
import com.sivalabs.jblogger.entities.Tag;
import com.sivalabs.jblogger.security.AuthenticatedUser;
import com.sivalabs.jblogger.security.SecurityUtils;
import com.sivalabs.jblogger.services.BlogService;
import com.sivalabs.jblogger.services.EmailService;
import com.sivalabs.jblogger.services.PostService;
import com.sivalabs.jblogger.services.TagService;

@Controller
public class AdminController
{	
	private final EmailService emailService;
	private final BlogService blogService;
	private final PostService postService;
	private final TagService tagService;

	@Autowired
	public AdminController(EmailService emailService, BlogService blogService,
						   PostService postService, TagService tagService) {
		this.emailService = emailService;
		this.blogService = blogService;
		this.postService = postService;
		this.tagService = tagService;
	}

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@GetMapping("/admin/dashboard")
	public String dashboard(@RequestParam(value="timePeriod", defaultValue="TODAY") String timePeriod,
							Model model)
	{
		BlogOverview overview = blogService.getBlogOverView(TimePeriod.fromString(timePeriod));
		model.addAttribute("overview", overview);
		return "admin/dashboard";
	}
	
	@GetMapping(value="/admin/posts/newpost")
	public String newPostForm(Model model) {
		Post post = new Post();
		model.addAttribute("post", post);
		return "admin/newpost";
	}
	
	@PostMapping(value="/admin/posts")
	public String createPost(@Valid @ModelAttribute("post") Post post, 
							BindingResult result, 
							Model model, HttpServletRequest request) {
		if(result.hasErrors()){
			model.addAttribute("post",post);
	        return "admin/newpost";
		}
		String url = getUrl(post);
		post.setUrl(url);
		
		String[] tagsString = request.getParameterValues("tagsList");
		if(tagsString != null){
			Set<Tag> tags = this.tokenize(tagsString);
			post.setTags(tags);
		}
		AuthenticatedUser loggedinUser = SecurityUtils.getCurrentUser();
		post.setCreatedBy(loggedinUser.getUser());
		Post createdPost = this.postService.createPost(post);
		
		String subject = "A new post created :"+post.getTitle();
		String content = "Content :\n"+post.getShortDescription();
		emailService.send(subject, content);
		
		return "redirect:/"+createdPost.getUrl();
	}
	
	@GetMapping("/admin/posts/{postId}/edit")
	public String editPostForm(@PathVariable("postId") Long postId,
							   HttpServletResponse response,
							   Model model) throws IOException {
		Optional<Post> post = postService.findPostById(postId);
		if(!post.isPresent())
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		model.addAttribute("post", post.get());
		return "admin/editpost";
	}
	
	@PostMapping("/admin/posts/{postId}")
	public String updatePost(@PathVariable("postId") Long postId,
							 @Valid @ModelAttribute("post") Post post,
							 BindingResult result,
							 Model model,
							 HttpServletRequest request,
							 HttpServletResponse response) throws IOException {
		if(result.hasErrors()){
			model.addAttribute("post",post);
	        return "admin/editpost";
		}
		Optional<Post> oldPostObj = postService.findPostById(postId);
		if(!oldPostObj.isPresent())
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		final Post oldPost = oldPostObj.get();
		oldPost.setTitle(post.getTitle());
		oldPost.setShortDescription(post.getShortDescription());
		oldPost.setContent(post.getContent());
		
		String[] tagsString = request.getParameterValues("tagsList");
		if(tagsString != null){
			Set<Tag> tags = this.tokenize(tagsString);
			oldPost.setTags(tags);
		}
		
		oldPost.setUpdatedOn(LocalDateTime.now());
		Post updatedPost = this.postService.updatePost(oldPost);
				
		return "redirect:/"+updatedPost.getUrl();
	}
	
	@ResponseBody
	@DeleteMapping("/admin/posts/{postId}/delete")
	public String deletePost(@PathVariable("postId") Long postId)
	{
		postService.deletePost(postId);		
		return "success";
	}
	
	@GetMapping("/admin/posts")
	public String posts(Model model)
	{
		model.addAttribute("posts", postService.findAllPosts());
		return "admin/posts";
	}
	
	@GetMapping("/admin/comments")
	public String comments(Model model)
	{
		model.addAttribute("comments", postService.findAllComments());
		return "admin/comments";
	}
	
	@PostMapping("/admin/comments/delete")
	public String deleteComments(HttpServletRequest request)
	{
		String[] commentIds = request.getParameterValues("comments");
		for (String id : commentIds) {
			postService.deleteComment(Long.valueOf(id));
		}
		return "redirect:/admin/comments";
	}
	
	@GetMapping("/admin/tags")
	public String tags(Model model)
	{
		model.addAttribute("tags", tagService.findAllTags());
		return "admin/tags";
	}
	
	@GetMapping("/admin/tagsJson")
	@ResponseBody
	public List<Tag> tagsJSON()
	{
		return tagService.findAllTags();
	}
	
	private static String getUrl(Post post)
	{
		String title = post.getTitle().trim().toLowerCase();
		String url = title.replaceAll("\\s+", "-");
		url = url.replaceAll("[^A-Za-z0-9]", "-");
		url = url.replaceAll("-+", "-");
		return url;
	}
	
	public Set<Tag> tokenize(String[] labelsArr) {
		Set<Tag> tags = new HashSet<>();
		for (String label : labelsArr)
		{
			if(StringUtils.isEmpty(label)) continue;
			Tag tag;
			try {
				Long tagId = Long.parseLong(label.trim());
				tag = tagService.findById(tagId).orElse(null);
			}catch (NumberFormatException e){
				tag = tagService.findByLabel(label.trim()).orElse(null);
			}
			if(tag == null) {
				tag = new Tag();
				tag.setLabel(label.trim());
				tag = tagService.createTag(tag);
			}
			tags.add(tag);
		}
		return tags;
	}
}
