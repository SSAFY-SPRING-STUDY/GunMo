package com.ll.study.entity;

import lombok.Getter;

@Getter

public class PostEntity {
    private static Long AUTO_INCREMENT = 1L;
    //entity는 db와 연동돼서 entity는 컨트롤러에 넘어가지 않는다.
    //repository에서는 entity를 출력하더라도 service에서는 PostResponse만 출력.
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostEntity(String title, String content, String author) {
        this.id = AUTO_INCREMENT++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
