package com.posting.comment.entity;

import com.posting.posting.entity.Posting;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    
    // 게시글이 있어야만 댓글도 존재할 수 있다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)    // optional은 JPA에서 null의 허용 여부
    @JoinColumn(name = "posting_id", nullable = false)      // nullable은 DB에서 null의 허용 여부
    private Posting posting;

    public Comment(String content) {
        this.content = content;
    }
}
