package com.ll.study;

public class CreatePostRequest {
    private final String title;
    private final String content;
    private final String author;

    @Override
    public String toString() {
        return "CreatePostRequest{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public CreatePostRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
