package com.example.jiseon_hw4.post;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void save(Post post) {
        // Post 객체 DB에 저장
        postRepository.save(post);
    }

    // 게시글 전체 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 게시글 제목으로 조회
    public List<Post> findByPostname(String postname) {
        return postRepository.findByPostname(postname);
    }

    // 게시글 삭제
    public void deleteByPostname(String postname) {
        postRepository.deleteByPostname(postname);
    }
}
