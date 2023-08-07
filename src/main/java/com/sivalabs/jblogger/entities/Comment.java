package com.sivalabs.jblogger.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "COMMENTS")
@Data
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    @NotEmpty
    private String name;

    @Column(name = "email", nullable = false, length = 150)
    @NotEmpty
    @Email
    private String email;

    @Column(name = "content", nullable = false)
    @NotEmpty
    private String content;

    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
