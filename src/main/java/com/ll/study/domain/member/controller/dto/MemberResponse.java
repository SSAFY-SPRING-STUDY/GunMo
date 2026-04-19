package com.ll.study.domain.member.controller.dto;

import com.ll.study.domain.member.entity.MemberEntity;

public record MemberResponse(long id, String loginId, String name) {
    public static MemberResponse fromEntity(MemberEntity entity) {
        return new MemberResponse(
                entity.getId(),
                entity.getLonginId(),
                entity.getName()
        );
    }
}
