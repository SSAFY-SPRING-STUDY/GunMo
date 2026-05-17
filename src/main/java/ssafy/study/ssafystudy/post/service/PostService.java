package ssafy.study.ssafystudy.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.study.ssafystudy.global.exception.CustomException;
import ssafy.study.ssafystudy.global.exception.error.ErrorCode;
import ssafy.study.ssafystudy.member.entity.MemberEntity;
import ssafy.study.ssafystudy.member.repository.MemberRepository;
import ssafy.study.ssafystudy.post.controller.dto.PostRequest;
import ssafy.study.ssafystudy.post.controller.dto.PostResponse;
import ssafy.study.ssafystudy.post.entity.PostEntity;
import ssafy.study.ssafystudy.post.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostResponse create(PostRequest request, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity postEntity = request.toEntity(author);

        PostEntity saved = postRepository.save(postEntity);
        return PostResponse.from(saved);
    }

    public List<PostResponse> getALlPost() {
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .toList();
    }

    public PostResponse getPostById(Long id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        return PostResponse.from(post);
    }

    @Transactional
    public PostResponse update(PostRequest request, Long id, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!post.getAuthor().getId().equals(author.getId())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        post.update(request.getTitle(), request.getContent());

        return PostResponse.from(post);
    }

    public void delete(Long id, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!post.getAuthor().getId().equals(author.getId())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        postRepository.delete(post);
    }
}