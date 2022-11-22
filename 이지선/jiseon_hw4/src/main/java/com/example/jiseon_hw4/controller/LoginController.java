package com.example.jiseon_hw4.controller;

import com.example.jiseon_hw4.member.Member;
import com.example.jiseon_hw4.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    @GetMapping("/signin")
    public String signInPage(Model model){
        Member member = new Member();
        model.addAttribute("member", member);
        return "signin";
    }

    // form이 POST로 전송될 때
    @PostMapping("/signin")
    public ResponseEntity signin(@ModelAttribute Member member, HttpServletRequest request) throws URISyntaxException {
        // 비밀번호 검사
        memberService.validate(member);

        // 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute("sessionUsername", member.getUsername());
        return ResponseEntity.status(HttpStatus.FOUND).location(new URI("/")).build();
    }

    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute(new Member());
        // model.addAttribute("member", new Member());  위와 동일한 코드
        return "signup";
    }

    @PostMapping("/signup")
    public String signUP(@ModelAttribute @Valid Member member, BindingResult bindingResult) {
        // 값이 이상하면 signup 페이지를 다시 불러옴
        if(bindingResult.hasErrors()) {
            return "signup";
        }
        memberService.save(member);
        return "redirect:/#signup=ok";      // # 이후의 코드는 그냥 개발자가 알아보기 쉽게
    }

    @PostMapping("/signout")
    public String signOut(HttpServletRequest request) {
        // 세션 만료
        request.getSession().invalidate();
        return "redirect:/";
    }
}
