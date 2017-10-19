package com.sivalabs.jblogger.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Siva
 *
 */
public enum TimePeriod
{
	TODAY, YESTERDAY, WEEK, MONTH, ALL_TIME;
	
	private static final Logger logger = LoggerFactory.getLogger(TimePeriod.class);
	
	public static TimePeriod fromString(String value) {
		try
		{
			return TimePeriod.valueOf(value);
		} catch (Exception e)
		{
			logger.error(e.getMessage(),e);
			return TimePeriod.TODAY;
		}
	}
}
