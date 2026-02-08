package com.posting.posting.service;

import com.posting.posting.dto.*;
import com.posting.posting.entity.Posting;
import com.posting.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingService {

    private final PostingRepository postingRepository;

    @Transactional
    public CreatePostingResponse save(CreatePostingRequest request) {
        Posting posting = new Posting(request.getTitle(), request.getContent());
        Posting savedPosting = postingRepository.save(posting);
        return new CreatePostingResponse(
                savedPosting.getId(),
                savedPosting.getTitle(),
                savedPosting.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<GetPostingResponse> getAll() {
        List<Posting> postings = postingRepository.findAll();
        List<GetPostingResponse> dtos = new ArrayList<>();
        for (Posting posting : postings) {
            GetPostingResponse dto = new GetPostingResponse(
                    posting.getId(),
                    posting.getTitle()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetPostingResponse getOne(Long postingId) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalStateException("없는 게시글입니다.")
        );
        return new GetPostingResponse(
                posting.getId(),
                posting.getTitle()
        );
    }

    @Transactional
    public @Nullable UpdatePostingResponse update(Long postingId, UpdatePostingRequest request) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalStateException("없는 게시글입니다.")
        );
        posting.update(request.getTitle(), request.getContent());
        return new UpdatePostingResponse(posting.getId());
    }

    @Transactional
    public void delete(Long postingId) {
        boolean existence = postingRepository.existsById((postingId));
        if (!existence) {
            throw new IllegalStateException("없는 게시글입니다.");
        }
        postingRepository.deleteById(postingId);
    }
}
