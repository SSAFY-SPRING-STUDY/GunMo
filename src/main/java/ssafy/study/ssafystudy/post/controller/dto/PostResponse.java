package ssafy.study.ssafystudy.post.controller.dto;

import ssafy.study.ssafystudy.post.entity.PostEntity;

public record PostResponse(long Id, String title, String content, String author) {
    public static PostResponse from(PostEntity postEntity){
        return new PostResponse(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getAuthor()
        );
    }
}
