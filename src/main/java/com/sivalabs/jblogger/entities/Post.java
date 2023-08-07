package com.sivalabs.jblogger.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Entity
@Table(name = "POSTS")
@Data
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    @NotEmpty
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "content", nullable = false)
    @NotEmpty
    private String content;

    @Column(name = "short_desc", length = 500)
    @NotEmpty
    private String shortDescription;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "view_count")
    private Long viewCount = 0L;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "post_tag",
            joinColumns = {@JoinColumn(name = "POST_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "TAG_ID", referencedColumnName = "ID")})
    private Set<Tag> tags;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public String getTagIdsAsString() {
        if (tags == null || tags.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        for (Tag tag : tags) {
            sb.append("," + tag.getId());
        }
        return sb.substring(1);
    }

    public String getTagsAsString() {
        if (tags == null || tags.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        for (Tag tag : tags) {
            sb.append("," + tag.getLabel());
        }
        return sb.substring(1);
    }

    public String[] getTagsAsStringArray() {
        if (tags == null || tags.isEmpty()) return new String[] {};

        String[] arr = new String[tags.size()];
        int i = 0;
        for (Tag tag : tags) {
            arr[i++] = tag.getLabel();
        }

        return arr;
    }

    public String[] getTagIdsAsStringArray() {
        if (tags == null || tags.isEmpty()) return new String[] {};

        String[] arr = new String[tags.size()];
        int i = 0;
        for (Tag tag : tags) {
            arr[i++] = "" + tag.getId();
        }

        return arr;
    }
}
