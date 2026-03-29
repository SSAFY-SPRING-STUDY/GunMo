package com.ll.study.controller;

import com.ll.study.controller.dto.PostRequest;
import com.ll.study.controller.dto.PostResponse;
import com.ll.study.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/posts")
    public PostResponse createPost(@RequestBody PostRequest request) {
        PostResponse response = postService.save(request);

        return response;
    }
    @GetMapping("/api/posts")
    public List<PostResponse> findAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/api/posts/{id}")
    public PostResponse findPostById(@PathVariable Long id) {
        PostResponse response = postService.findById(id);
        return response;
    }

    @PutMapping("/api/posts/{id}")
    public PostResponse updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        PostResponse response = postService.update(id, request);
        return response;
    }

    @DeleteMapping("/api/posts/{id}")
    public PostResponse deletePost(@PathVariable Long id) {
        PostResponse response = postService.delete(id);
        return response;
    }
}