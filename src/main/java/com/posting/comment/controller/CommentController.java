package com.posting.comment.controller;

import com.posting.comment.dto.CreateCommentRequest;
import com.posting.comment.dto.CreateCommentResponse;
import com.posting.comment.dto.GetCommentResponse;
import com.posting.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("postings/{postingId}/comments")
    public ResponseEntity<CreateCommentResponse> saveComment(
            @PathVariable Long postingId,
            @RequestBody CreateCommentRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(postingId, request));
    }

    @GetMapping("/postings/{postingId}/comments")
    public ResponseEntity<List<GetCommentResponse>> getComments(
            @PathVariable Long postingId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAll(postingId));
    }

}
