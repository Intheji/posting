package com.posting.posting.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GetPostingResponse {

    private final Long id;
    private final String title;

    public GetPostingResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
