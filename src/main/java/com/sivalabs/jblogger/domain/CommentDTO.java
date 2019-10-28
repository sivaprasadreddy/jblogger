package com.sivalabs.jblogger.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDTO {
    private Long id;
    private String name;
    private String email;
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
