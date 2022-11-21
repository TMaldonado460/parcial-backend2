package com.digitalmedia.movies.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String text;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Comment(String username, String text, LocalDateTime timestamp) {
        this.username = username;
        this.text = text;
        this.timestamp = timestamp;
    }
}