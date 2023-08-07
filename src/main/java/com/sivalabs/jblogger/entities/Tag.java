package com.sivalabs.jblogger.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "TAGS")
@Data
public class Tag implements Serializable, Comparable<Tag> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", unique = true, nullable = false, length = 150)
    private String label;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;

    @Override
    public int compareTo(Tag other) {
        return this.label.compareToIgnoreCase(other.label);
    }
}
