package com.sivalabs.jblogger.domain;

import java.util.List;
import lombok.Data;

@Data
public class PostsResponse {
    private List<PostDTO> posts;
    private int currentPageNo;
    private int totalPages;
    private int totalPosts;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}
