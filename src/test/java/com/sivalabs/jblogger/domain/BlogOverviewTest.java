package com.sivalabs.jblogger.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sivalabs.jblogger.entities.PageView;
import com.sivalabs.jblogger.entities.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BlogOverviewTest {

    @Test
    void getPostViewCountMapShouldReturnEmptyMapWhenNoPageViews() {
        BlogOverview overview = new BlogOverview();
        final Map<Post, Long> viewCountMap = overview.getPostViewCountMap();
        assertThat(viewCountMap).isEmpty();
    }

    @Test
    void getPostViewCountMap() {
        BlogOverview overview = new BlogOverview();
        List<PageView> pageViews = new ArrayList<>();

        final PageView pageView1 = new PageView();
        Post p1 = new Post();
        p1.setId(1L);
        pageView1.setPost(p1);
        pageViews.add(pageView1);

        final PageView pageView2 = new PageView();
        pageView2.setPost(p1);
        pageViews.add(pageView2);

        final PageView pageView3 = new PageView();
        Post p2 = new Post();
        p2.setId(2L);
        pageView3.setPost(p2);
        pageViews.add(pageView3);

        overview.setPageViews(pageViews);

        final Map<Post, Long> viewCountMap = overview.getPostViewCountMap();
        assertThat(viewCountMap.get(p1)).isEqualTo(2);
        assertThat(viewCountMap.get(p2)).isEqualTo(1);
    }
}
