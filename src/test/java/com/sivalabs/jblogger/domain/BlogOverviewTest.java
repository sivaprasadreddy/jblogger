package com.sivalabs.jblogger.domain;

import com.sivalabs.jblogger.entities.PageView;
import com.sivalabs.jblogger.entities.Post;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BlogOverviewTest {
    @Test
    public void getPostViewCountMapShouldReturnEmptyMapWhenNoPageViews() throws Exception {
        BlogOverview overview  = new BlogOverview();
        final Map<Post, Long> viewCountMap = overview.getPostViewCountMap();
        assertTrue(viewCountMap.isEmpty());
    }

    @Test
    public void getPostViewCountMap() throws Exception {
        BlogOverview overview  = new BlogOverview();
        List<PageView> pageViews = new ArrayList<>();

        final PageView pageView1 = new PageView();
        Post p1 = new Post();
        p1.setId(1);
        pageView1.setPost(p1);
        pageViews.add(pageView1);

        final PageView pageView2 = new PageView();
        pageView2.setPost(p1);
        pageViews.add(pageView2);

        final PageView pageView3 = new PageView();
        Post p2 = new Post();
        p2.setId(2);
        pageView3.setPost(p2);
        pageViews.add(pageView3);

        overview.setPageViews(pageViews);

        final Map<Post, Long> viewCountMap = overview.getPostViewCountMap();
        assertTrue(viewCountMap.get(p1) == 2);
        assertTrue(viewCountMap.get(p2) == 1);
    }

}
