package com.posting.comment.repository;

import com.posting.comment.entity.Comment;
import com.posting.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPosting(Posting posting);
}
