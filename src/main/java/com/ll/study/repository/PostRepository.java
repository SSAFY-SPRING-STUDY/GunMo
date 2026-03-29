package com.ll.study.repository;

import com.ll.study.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    List<PostEntity> postList = new ArrayList<>();
    private Long nextId = 1L;
    public PostEntity save(PostEntity postEntity) {
        postList.add(postEntity);
        postEntity.setId(nextId++);

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
