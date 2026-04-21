package com.ll.study.domain.member.service;

import com.ll.study.domain.member.controller.dto.MemberRequest;
import com.ll.study.domain.member.controller.dto.MemberResponse;
import com.ll.study.domain.member.entity.MemberEntity;
import com.ll.study.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse save (MemberRequest request){
        MemberEntity entity = MemberRequest.toEntity(request);
        MemberEntity savedEntity = memberRepository.save(entity);
        return MemberResponse.fromEntity(savedEntity);
    }

    public MemberResponse findById(Long memberId) {
        MemberEntity entity = memberRepository.findById(memberId).
                orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다.")
        );
        return MemberResponse.fromEntity(entity);
    }
}
