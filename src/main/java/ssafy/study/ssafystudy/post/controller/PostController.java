package ssafy.study.ssafystudy.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.study.ssafystudy.auth.component.SessionManager;
import ssafy.study.ssafystudy.auth.util.AuthTokenUtils;
import ssafy.study.ssafystudy.global.exception.CustomException;
import ssafy.study.ssafystudy.global.exception.error.ErrorCode;
import ssafy.study.ssafystudy.post.controller.dto.PostRequest;
import ssafy.study.ssafystudy.post.controller.dto.PostResponse;
import ssafy.study.ssafystudy.post.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final SessionManager sessionManager;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody PostRequest request
    ) {
        Long authorId = getAuthorIdFromToken(bearerToken);
        return postService.create(request, authorId);
    }

    @GetMapping
    public List<PostResponse> getAllPosts() {
        return postService.getALlPost();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        try {
            PostResponse response = postService.getPostById(postId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Long postId,
            @RequestBody PostRequest request
    ) {
        try {
            Long authorId = getAuthorIdFromToken(bearerToken);
            PostResponse response = postService.update(request, postId, authorId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Long postId
    ) {
        try {
            Long authorId = getAuthorIdFromToken(bearerToken);
            postService.delete(postId, authorId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private Long getAuthorIdFromToken(String bearerToken) {
        if (!AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        return sessionManager.getMemberId(sessionKey);
    }
}