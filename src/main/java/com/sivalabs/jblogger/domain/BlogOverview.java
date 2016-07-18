/**
 * 
 */
package com.sivalabs.jblogger.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sivalabs.jblogger.core.entities.PageView;
import com.sivalabs.jblogger.core.entities.Post;

/**
 * @author Siva
 *
 */
public class BlogOverview
{
	private long postsCount;
	private long commentsCount;
	private long todayViewCount;
	private long yesterdayViewCount;
	private long thisWeekViewCount;
	private long thisMonthViewCount;
	private long alltimeViewCount;
	
	private List<PageView> pageViews;
	
	public long getPostsCount()
	{
		return postsCount;
	}
	public void setPostsCount(long postsCount)
	{
		this.postsCount = postsCount;
	}
	public long getCommentsCount()
	{
		return commentsCount;
	}
	public void setCommentsCount(long commentsCount)
	{
		this.commentsCount = commentsCount;
	}
	public long getTodayViewCount()
	{
		return todayViewCount;
	}
	public void setTodayViewCount(long todayViewCount)
	{
		this.todayViewCount = todayViewCount;
	}
	public long getYesterdayViewCount()
	{
		return yesterdayViewCount;
	}
	public void setYesterdayViewCount(long yesterdayViewCount)
	{
		this.yesterdayViewCount = yesterdayViewCount;
	}
	public long getThisWeekViewCount()
	{
		return thisWeekViewCount;
	}
	public void setThisWeekViewCount(long thisWeekViewCount)
	{
		this.thisWeekViewCount = thisWeekViewCount;
	}
	public long getThisMonthViewCount()
	{
		return thisMonthViewCount;
	}
	public void setThisMonthViewCount(long thisMonthViewCount)
	{
		this.thisMonthViewCount = thisMonthViewCount;
	}
	public long getAlltimeViewCount()
	{
		return alltimeViewCount;
	}
	public void setAlltimeViewCount(long alltimeViewCount)
	{
		this.alltimeViewCount = alltimeViewCount;
	}
	public List<PageView> getPageViews()
	{
		return pageViews;
	}
	public void setPageViews(List<PageView> pageViews)
	{
		this.pageViews = pageViews;
	}
	
	public Map<Post, Long> getPostViewCountMap()
	{
		Map<Post, Long>  map = new HashMap<>();
		if(this.pageViews == null) return map;
		
		for (PageView pageView : pageViews)
		{
			Post post = pageView.getPost();
			if(!map.containsKey(post)){
				map.put(post, 0L);
			}
			map.put(post, map.get(post)+1);
		}
		return map;
	}
}
