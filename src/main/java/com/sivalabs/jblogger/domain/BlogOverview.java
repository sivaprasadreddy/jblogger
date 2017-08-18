package com.sivalabs.jblogger.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sivalabs.jblogger.entities.PageView;
import com.sivalabs.jblogger.entities.Post;
import lombok.Data;

/**
 * @author Siva
 *
 */
@Data
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
