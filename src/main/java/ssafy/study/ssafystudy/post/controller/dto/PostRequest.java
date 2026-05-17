package ssafy.study.ssafystudy.post.controller.dto;

import ssafy.study.ssafystudy.member.entity.MemberEntity;
import ssafy.study.ssafystudy.post.entity.PostEntity;

public class PostRequest {
    private String title;
    private String content;

    public PostRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
    }
    public PostEntity toEntity(MemberEntity author) {
        return PostEntity.create(title, content, author);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
