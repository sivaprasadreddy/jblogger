package com.sivalabs.jblogger.utils;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

/**
 * @author Siva
 *
 */
public class CommonUtilsTest
{
	@Test
	public void testStartOfDay() throws Exception
	{
		LocalDateTime startOfDay = CommonUtils.getStartOfDay(LocalDateTime.now());
		assertNotNull(startOfDay);
	}
}
