/**
 * 
 */
package com.sivalabs.jblogger.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;


/**
 * @author Siva
 *
 */
public class CommonUtils
{
	
	public static Date getEndOfDay(Date date) {
	    return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
	}

	public static Date getStartOfDay(Date date) {
	    return DateUtils.truncate(date, Calendar.DATE);
	}
	
	public static Date getYesterDay(Date date) {
	    return DateUtils.addDays(date, -1);
	}
	
	public static Date getWeekStartDay(Date date)
	{
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
		Calendar first = (Calendar) cal.clone();
	    first.add(Calendar.DAY_OF_WEEK, 
	              first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));
	    return first.getTime();
	}
	
	public static Date getWeekEndDay(Date date)
	{
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
		Calendar first = (Calendar) cal.clone();
	    first.add(Calendar.DAY_OF_WEEK, 
	              first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));
	    
	    // and add six days to the end date
	    Calendar last = (Calendar) first.clone();
	    last.add(Calendar.DAY_OF_YEAR, 6);
	    
	    return last.getTime();
	}
	
	public static Date getMonthStartDay(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
	    c.set(Calendar.DAY_OF_MONTH, 1);
	    return c.getTime();   
	}
	
	public static Date getMonthEndDay(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
	    
	    c.add(Calendar.MONTH, 1);  
        c.set(Calendar.DAY_OF_MONTH, 1);  
        c.add(Calendar.DATE, -1);  
        
	    return c.getTime();   
	}
	
	public static Date getDummyVeryOldDate()
	{
		Calendar c = Calendar.getInstance();
		 c.set(Calendar.YEAR, 1970);  
		 c.set(Calendar.MONTH, 1);  
		 c.set(Calendar.DATE, 1);
		 return c.getTime();
	}
	
	public static Date getDummyVeryNewDate()
	{
		Calendar c = Calendar.getInstance();
		 c.set(Calendar.YEAR, 9999);  
		 c.set(Calendar.MONTH, 1);  
		 c.set(Calendar.DATE, 1);
		 return c.getTime();
	}
	
	
}
