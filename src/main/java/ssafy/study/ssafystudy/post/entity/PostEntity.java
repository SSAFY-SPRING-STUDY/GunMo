package ssafy.study.ssafystudy.post.entity;

import lombok.Getter;
import ssafy.study.ssafystudy.member.entity.MemberEntity;

@Getter
public class PostEntity {
    private static long Auto_INCREMENT_ID = 1L;
    private Long id;
    private String title;
    private String content;
    private MemberEntity author;

    public PostEntity(String title, String content, MemberEntity author) {
        this.id = Auto_INCREMENT_ID++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static PostEntity create(String title, String content, MemberEntity author) {
        return new PostEntity(title, content, author);
    }

    public void update (String title, String content){
        this.title = title;
        this.content = content;
    }

}
