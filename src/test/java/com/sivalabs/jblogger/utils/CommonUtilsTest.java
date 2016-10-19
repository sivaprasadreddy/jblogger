/**
 * 
 */
package com.sivalabs.jblogger.utils;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.sivalabs.jblogger.utils.CommonUtils;

/**
 * @author Siva
 *
 */
public class CommonUtilsTest
{
	@Test
	public void testStartOfDay() throws Exception
	{
		Date startOfDay = CommonUtils.getStartOfDay(new Date());
		assertNotNull(startOfDay);
	}
}
