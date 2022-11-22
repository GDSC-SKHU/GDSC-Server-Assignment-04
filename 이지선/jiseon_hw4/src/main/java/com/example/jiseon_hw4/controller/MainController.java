package com.example.jiseon_hw4.controller;

import com.example.jiseon_hw4.member.Member;
import com.example.jiseon_hw4.member.MemberService;
import com.example.jiseon_hw4.post.Post;
import com.example.jiseon_hw4.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/")
    public String mainPage(Model model, @RequestParam(required = false) String query) {
        // 전체 게시글 조회
        if (query == null) {
            List<Post> posts = postService.findAll();
            model.addAttribute("posts", posts);
            return "home";
        }
        // query 값이 있을 경우(검색)
        // 검색한 제목의 post가 없을 때 NPE 처리는??
        List<Post> posts = postService.findByPostname(query);
        model.addAttribute("posts", posts);
        return "home";
    }

    // 더미 데이터 생성
    @PostConstruct
    public void createDummyData() {
        // 지금 사용한 passwordEncoder는 같은 비밀번호여도 다르게 암호화된다.
        memberService.save(new Member("admin", "0000"));
        memberService.save(new Member("이지선", "1234"));
    }
}
