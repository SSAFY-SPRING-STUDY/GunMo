package com.ll.study.controller;

import com.ll.study.controller.dto.PostRequest;
import com.ll.study.controller.dto.PostResponse;
import com.ll.study.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @PostMapping()
    public PostResponse createPost(@RequestBody PostRequest request) {
        PostResponse response = postService.save(request);

        return response;
    }
    @GetMapping()
    public List<PostResponse> findAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public PostResponse findPostById(@PathVariable Long id) {
        PostResponse response = null;
        try{
            response = postService.findById(id);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return response;
    }

    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        PostResponse response = postService.update(id, request);
        return response;
    }

    @DeleteMapping("/{id}")
    public PostResponse deletePost(@PathVariable Long id) {
        PostResponse response = postService.delete(id);
        return response;
    }
}