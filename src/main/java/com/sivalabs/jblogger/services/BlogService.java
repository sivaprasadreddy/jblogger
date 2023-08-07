package com.sivalabs.jblogger.services;

import com.sivalabs.jblogger.domain.BlogOverview;
import com.sivalabs.jblogger.domain.TimePeriod;
import com.sivalabs.jblogger.entities.PageView;
import com.sivalabs.jblogger.repositories.CommentRepository;
import com.sivalabs.jblogger.repositories.PageViewRepository;
import com.sivalabs.jblogger.repositories.PostRepository;
import com.sivalabs.jblogger.utils.CommonUtils;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PageViewRepository pageViewRepository;

    @Autowired
    public BlogService(
            PostRepository postRepository, CommentRepository commentRepository, PageViewRepository pageViewRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.pageViewRepository = pageViewRepository;
    }

    public BlogOverview getBlogOverView(TimePeriod timePeriod) {
        BlogOverview overview = new BlogOverview();
        long postsCount = postRepository.count();
        overview.setPostsCount(postsCount);

        long commentsCount = commentRepository.count();
        overview.setCommentsCount(commentsCount);

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startDate = CommonUtils.getStartOfDay(today);
        LocalDateTime endDate = CommonUtils.getEndOfDay(today);

        long todayViewCount = pageViewRepository.countByVisitTimeBetween(startDate, endDate);
        overview.setTodayViewCount(todayViewCount);

        LocalDateTime yesterday = CommonUtils.getYesterday(today);
        startDate = CommonUtils.getStartOfDay(yesterday);
        endDate = CommonUtils.getEndOfDay(yesterday);
        long yesterdayViewCount = pageViewRepository.countByVisitTimeBetween(startDate, endDate);
        overview.setYesterdayViewCount(yesterdayViewCount);

        startDate = CommonUtils.getWeekStartDay(today);
        endDate = CommonUtils.getWeekEndDay(today);

        long thisWeekViewCount = pageViewRepository.countByVisitTimeBetween(startDate, endDate);
        overview.setThisWeekViewCount(thisWeekViewCount);

        startDate = CommonUtils.getMonthStartDay(today);
        endDate = CommonUtils.getMonthEndDay(today);

        long thisMonthViewCount = pageViewRepository.countByVisitTimeBetween(startDate, endDate);
        overview.setThisMonthViewCount(thisMonthViewCount);

        long alltimeViewCount = postRepository.getTotalPostViewCount();
        overview.setAlltimeViewCount(alltimeViewCount);

        if (timePeriod == TimePeriod.ALL_TIME) {
            startDate = CommonUtils.getDummyVeryOldDate();
            endDate = CommonUtils.getDummyVeryNewDate();
        } else if (timePeriod == TimePeriod.MONTH) {
            startDate = CommonUtils.getMonthStartDay(today);
            endDate = CommonUtils.getMonthEndDay(today);
        } else if (timePeriod == TimePeriod.WEEK) {
            startDate = CommonUtils.getWeekStartDay(today);
            endDate = CommonUtils.getWeekEndDay(today);
        } else if (timePeriod == TimePeriod.YESTERDAY) {
            startDate = CommonUtils.getStartOfDay(yesterday);
            endDate = CommonUtils.getEndOfDay(yesterday);
        } else {
            startDate = CommonUtils.getStartOfDay(today);
            endDate = CommonUtils.getEndOfDay(today);
        }

        List<PageView> pageViews = pageViewRepository.findByVisitTimeBetween(startDate, endDate);
        overview.setPageViews(pageViews);
        return overview;
    }
}
