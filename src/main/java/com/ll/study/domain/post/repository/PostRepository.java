package com.ll.study.domain.post.repository;

import com.ll.study.domain.post.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    List<PostEntity> postList = new ArrayList<>();

    public PostEntity save(PostEntity postEntity) {
        postList.add(postEntity);

        return postEntity;
    }

    public List<PostEntity> findAll() {
        return postList;
    }

    public Optional<PostEntity> findById(Long id) {
        for (PostEntity post : postList) {
            if (post.getId().equals(id)) {
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        postList.removeIf(post -> post.getId().equals(id));
    }

}
