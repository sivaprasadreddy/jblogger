package com.sivalabs.jblogger.domain;

import com.sivalabs.jblogger.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostsResponse {
    private List<Post> posts;
    private int currentPageNo;
    private int totalPages;
    private int totalPosts;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}
