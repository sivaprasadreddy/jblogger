/**
 * 
 */
package com.sivalabs.jblogger.site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Siva
 *
 */
@Controller
public class ErrorController extends BaseController
{
	
	@RequestMapping("/page-not-found")
	public String notFound()
	{
		return "404";
	}

	@RequestMapping("/serverError")
	public String serverError()
	{
		return "serverError";
	}
}
