package com.ll.study.domain.post.dto;

import com.ll.study.domain.post.entity.PostEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequest {
    private final String title;
    private final String content;
    private final String author;


    public static PostEntity toEntity(PostRequest postRequest) {
        return new PostEntity(postRequest.getTitle(), postRequest.getContent(), postRequest.getAuthor());
    }
}
