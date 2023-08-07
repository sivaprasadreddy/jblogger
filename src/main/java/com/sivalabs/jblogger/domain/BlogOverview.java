package com.sivalabs.jblogger.domain;

import com.sivalabs.jblogger.entities.PageView;
import com.sivalabs.jblogger.entities.Post;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class BlogOverview {
    private long postsCount;
    private long commentsCount;
    private long todayViewCount;
    private long yesterdayViewCount;
    private long thisWeekViewCount;
    private long thisMonthViewCount;
    private long alltimeViewCount;
    private List<PageView> pageViews;

    public Map<Post, Long> getPostViewCountMap() {
        Map<Post, Long> map = new HashMap<>();
        if (this.pageViews == null) return map;

        for (PageView pageView : pageViews) {
            Post post = pageView.getPost();
            map.compute(post, (k, v) -> (v == null) ? 1 : v + 1);
        }
        return map;
    }
}
