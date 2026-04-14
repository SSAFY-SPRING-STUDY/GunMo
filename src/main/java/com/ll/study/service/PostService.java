package com.ll.study.service;

import com.ll.study.controller.dto.PostRequest;
import com.ll.study.controller.dto.PostResponse;
import com.ll.study.entity.PostEntity;
import com.ll.study.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse save(PostRequest request) {
        PostEntity entity = new PostEntity(request.getTitle(), request.getContent(), request.getAuthor());
        PostEntity returnedEntity = postRepository.save(entity);

        PostResponse response = PostResponse.fromEntity(returnedEntity);

        return response;
    }

    public List<PostResponse> findAll() {
        List<PostEntity> entityList = postRepository.findAll();
        List<PostResponse> responseList = new ArrayList<>();

        for (PostEntity entity : entityList) {
            PostResponse response = PostResponse.fromEntity(entity);
            responseList.add(response);
        }
        return responseList;
    }


    public PostResponse findById(Long id) {
        PostEntity entity = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));

        PostResponse response = PostResponse.fromEntity(entity);

        return response;
    }

    public PostResponse update(Long id, PostRequest request) {
        //postRepository.update(id, request);
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        entity.updateTilteAndContent(request.getTitle(), request.getContent());


        return new PostResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthor()
        );
    }

    public PostResponse delete(Long id) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        postRepository.deleteById(id);
        PostResponse response = new PostResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthor()
        );
        return response;
    }
}
