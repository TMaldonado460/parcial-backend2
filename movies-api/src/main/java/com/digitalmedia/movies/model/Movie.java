package com.digitalmedia.movies.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Movie {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Id
    private String imdbId;
    private String title;
    private String director;
    private String release_year;
    private String poster;
    @OneToMany(mappedBy = "movie")
    private List<Comment> comments;


}