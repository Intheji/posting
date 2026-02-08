package com.posting.posting.controller;

import com.posting.posting.dto.CreatePostingRequest;
import com.posting.posting.dto.CreatePostingResponse;
import com.posting.posting.dto.GetPostingResponse;
import com.posting.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    @PostMapping("/postings")
    public ResponseEntity<CreatePostingResponse> createPosting(
            @RequestBody CreatePostingRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(postingService.save(request));
    }

    @GetMapping("/postings")
    public ResponseEntity<List<GetPostingResponse>> getPostings() {
        return ResponseEntity.status(HttpStatus.OK).body(postingService.getAll());
    }
}
