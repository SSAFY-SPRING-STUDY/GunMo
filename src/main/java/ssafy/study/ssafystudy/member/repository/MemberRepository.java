package ssafy.study.ssafystudy.member.repository;

import org.springframework.stereotype.Repository;
import ssafy.study.ssafystudy.member.entity.MemberEntity;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {
    private final ConcurrentHashMap<Long, MemberEntity> store = new ConcurrentHashMap<>();

    public MemberEntity save(MemberEntity member){
        store.put(member.getId(), member);
        return member;
    }

    public Optional<MemberEntity> findByUsername(String username){
        return store.values().stream()
                .filter(member -> member.getUsername().equals(username))
                .findFirst();
    }

    public Optional<MemberEntity> findById(Long memberId){
        return Optional.ofNullable(store.get(memberId));
    }
}
