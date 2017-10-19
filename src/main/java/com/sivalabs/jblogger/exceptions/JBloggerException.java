package com.sivalabs.jblogger.exceptions;

/**
 * @author Siva
 *
 */
public class JBloggerException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public JBloggerException()
	{
	}

	public JBloggerException(String message)
	{
		super(message);
	}

	public JBloggerException(Throwable cause)
	{
		super(cause);
	}

	public JBloggerException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
