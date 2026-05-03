package ssafy.study.ssafystudy.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.study.ssafystudy.post.controller.dto.PostRequest;
import ssafy.study.ssafystudy.post.controller.dto.PostResponse;
import ssafy.study.ssafystudy.post.entity.PostEntity;
import ssafy.study.ssafystudy.post.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponse save(PostRequest request){
        PostEntity postEntity = new PostEntity(
                request.getTitle(),
                request.getContent(),
                request.getAuthor()
        );

        PostEntity saved = postRepository.save(postEntity);
        return PostResponse.from(saved);
    }

    public List<PostResponse> getALlPost(){
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .toList();
    }

    public PostResponse findById(Long postId){
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        return PostResponse.from(post);
    }

    public PostResponse update(PostRequest requst, Long postId){
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        post.update(requst.getTitle(), requst.getContent());
        return PostResponse.from(post);
    }

    public void delete(Long postId){
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        postRepository.deleteById(postId);
    }
}
