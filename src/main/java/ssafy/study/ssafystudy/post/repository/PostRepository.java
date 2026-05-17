package ssafy.study.ssafystudy.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.study.ssafystudy.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}