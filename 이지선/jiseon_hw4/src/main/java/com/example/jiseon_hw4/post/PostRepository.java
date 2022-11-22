package com.example.jiseon_hw4.post;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostRepository {
    // 게시글 DB
    public static Map<String, Post> postMap = new HashMap<>();

    // 게시글 저장
    public void save(Post post) {
        postMap.put(post.getPostname(), post);
    }

    // 게시글 전체 조회
    public List<Post> findAll() {
        Collection<Post> values = postMap.values();
        return new ArrayList<>(values);
    }

    // 제목으로 찾기
    public List<Post> findByPostname(String postname) {
        return List.of(postMap.get(postname));
    }

    // 게시글 삭제
    public void deleteByPostname(String postname) {
        postMap.remove(postname);
    }
}
