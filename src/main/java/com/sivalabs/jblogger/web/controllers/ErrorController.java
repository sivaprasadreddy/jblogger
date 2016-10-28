/**
 * 
 */
package com.sivalabs.jblogger.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Siva
 *
 */
@Controller
public class ErrorController extends BaseController
{
	@RequestMapping("/403")
	public String accessDenied()
	{
		return "403";
	}
	
	@RequestMapping("/page-not-found")
	public String notFound()
	{
		return "404";
	}

	@RequestMapping("/serverError")
	public String serverError()
	{
		return "500";
	}
}
