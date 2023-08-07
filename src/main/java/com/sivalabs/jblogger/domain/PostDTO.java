package com.sivalabs.jblogger.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDTO {
    private Long id;
    private String title;
    private String url;
    private String content;
    private String shortDescription;
    private String createdByUserName;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Long viewCount;
    private List<String> tags;
    private List<CommentDTO> comments;

    private String twitterShareLink;
    private String facebookShareLink;
    private String linkedInShareLink;
}
