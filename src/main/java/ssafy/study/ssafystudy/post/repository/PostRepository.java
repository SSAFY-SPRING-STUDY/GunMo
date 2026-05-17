package ssafy.study.ssafystudy.post.repository;

import org.springframework.stereotype.Repository;
import ssafy.study.ssafystudy.post.entity.PostEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    private final List<PostEntity> posts = new ArrayList<>();

    public PostEntity save(PostEntity postEntity){
        posts.add(postEntity);
        return postEntity;
    }

    public Optional<PostEntity> findById(Long postId){
        return posts.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst();
    }

    public List<PostEntity> findAll(){
        return posts;
    }

    public void deleteById(Long postId){
        posts.removeIf(post -> post.getId().equals(postId));
    }

    public void delete(PostEntity post) {
        posts.remove(post);
    }
}
