package com.ll.study.domain.member.entity;

import lombok.Getter;

@Getter
public class MemberEntity {
    private static long AUTO_INCREMENT = 1L;

    private long id;
    private String longinId;
    private String password;
    private String name;

    public MemberEntity(String longinId, String password, String name) {
        this.id = AUTO_INCREMENT++;
        this.longinId = longinId;
        this.password = password;
        this.name = name;
    }

    public boolean isVaildPassword(String password) {
        return this.password.equals(password);
    }
}
