package com.digitalmedia.movies.repository;

import com.digitalmedia.movies.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
