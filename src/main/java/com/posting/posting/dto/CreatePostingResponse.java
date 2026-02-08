package com.posting.posting.dto;

import lombok.Getter;

@Getter
public class CreatePostingResponse {

    private final Long id;
    private final String title;
    private final String content;

    public CreatePostingResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
