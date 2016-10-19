/**
 * 
 */
package com.sivalabs.jblogger.web.site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Siva
 *
 */
@Controller
public class HomeController extends BaseController
{	
	
	@RequestMapping("/")
	public String home()
	{
		return "redirect:/posts";
	}
	
}
