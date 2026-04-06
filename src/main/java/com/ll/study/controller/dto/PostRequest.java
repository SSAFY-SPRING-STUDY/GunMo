package com.ll.study.controller.dto;

import com.ll.study.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequest {
    private final String title;
    private final String content;
    private final String author;

    public static PostEntity toEntity(PostRequest postRequest) {
        return new PostEntity(postRequest.getTitle(), postRequest.getContent(), postRequest.getAuthor());
    }
}
