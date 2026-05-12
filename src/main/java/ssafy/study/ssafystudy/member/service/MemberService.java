package ssafy.study.ssafystudy.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.study.ssafystudy.member.controller.dto.MemberRequest;
import ssafy.study.ssafystudy.member.controller.dto.MemberResponse;
import ssafy.study.ssafystudy.member.entity.MemberEntity;
import ssafy.study.ssafystudy.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse join(MemberRequest request){
        MemberEntity member = request.toEntity();
        MemberEntity saveMember = memberRepository.sava(member);

        return MemberResponse.from(saveMember);
    }

    public MemberResponse getMemberInfo(Long memberId){
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        return MemberResponse.from(member);
    }
}
