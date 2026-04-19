package com.ll.study.domain.post.dto;


import com.ll.study.domain.post.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;

    public static PostResponse fromEntity(PostEntity entity) {
        return new PostResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthor()
        );
    }
}
