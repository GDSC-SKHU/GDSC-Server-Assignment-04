package com.example.jiseon_hw4.controller;

import com.example.jiseon_hw4.post.Post;
import com.example.jiseon_hw4.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 로그인 상태일 때만 게시글 등록 페이지 이동
    @GetMapping("/post")
    public String createPostPage(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if ( session == null || session.getAttribute("sessionUsername") == null){
            // session null -> 비로그인 사용자이므로 메인화면 돌려보냄
            return "redirect:/";
        }

        Post post = new Post();
        model.addAttribute("post", post);
        return "post";
    }

    // 게시글 등록
    @PostMapping("/post")
    public String createPost(@ModelAttribute @Valid Post post, BindingResult bindingResult) {
        // 값이 이상하면 페이지 다시 불러오기
        if(bindingResult.hasErrors()) {
            return "post";
        }
        postService.save(post);
        return "redirect:/";
    }

    // 게시글 상세
    @GetMapping("/detail/{postname}")
    public String detailPostPage(@PathVariable String postname, Model model) {
        List<Post> posts = postService.findByPostname(postname);
        model.addAttribute("posts", posts);
        return "detail";
    }

     // 게시글 삭제
     @GetMapping("/deletepost")
    public String deletePost(String postname) {
        postService.deleteByPostname(postname);
        return "redirect:/#delete=ok";
     }
}
