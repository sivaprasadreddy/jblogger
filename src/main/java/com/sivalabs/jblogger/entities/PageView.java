package com.sivalabs.jblogger.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "pageviews")
@Data
public class PageView extends Base implements Serializable {
    private String url;

    private String referrer;

    @Column(name = "visit_time")
    private LocalDateTime visitTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
