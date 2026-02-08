package com.posting.comment.service;

import com.posting.comment.dto.CreateCommentRequest;
import com.posting.comment.dto.CreateCommentResponse;
import com.posting.comment.dto.GetCommentResponse;
import com.posting.comment.entity.Comment;
import com.posting.comment.repository.CommentRepository;
import com.posting.posting.entity.Posting;
import com.posting.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostingRepository postingRepository;


    @Transactional
    public CreateCommentResponse save(Long postingId, CreateCommentRequest request) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalStateException("없는 게시글입니다.")
        );
        Comment comment = new Comment(request.getContent(), posting);
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getContent()
        );
    }

    @Transactional(readOnly = true)
    public @Nullable List<GetCommentResponse> getAll(Long postingId) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalStateException("없는 게시글입니다.")
        );

        List<Comment> comments = commentRepository.findByPosting(posting);
        return comments.stream()
                .map(comment -> new GetCommentResponse(
                        comment.getId(),
                        comment.getContent()
                ))
                .toList();
    }
}
