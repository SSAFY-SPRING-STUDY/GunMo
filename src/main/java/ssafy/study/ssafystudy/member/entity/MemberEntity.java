package ssafy.study.ssafystudy.member.entity;

import lombok.Getter;

@Getter
public class MemberEntity {
    private static long AUTO_INCREMENT = 1L;

    private final Long id;
    private final String username;
    private final String password;
    private final String nickname;

    private MemberEntity(Long id, String username, String password, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public static MemberEntity create(String username, String password, String nickname){
        return new MemberEntity(AUTO_INCREMENT++, username, password, nickname);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
