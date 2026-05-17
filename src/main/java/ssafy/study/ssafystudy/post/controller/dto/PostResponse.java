package ssafy.study.ssafystudy.post.controller.dto;

import ssafy.study.ssafystudy.member.controller.dto.MemberResponse;
import ssafy.study.ssafystudy.member.entity.MemberEntity;
import ssafy.study.ssafystudy.post.entity.PostEntity;

public record PostResponse(long id, String title, String content, MemberResponse memberResponse) {
    public static PostResponse from(PostEntity postEntity){
        return new PostResponse(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                MemberResponse.from(postEntity.getAuthor())
        );
    }
}
