package com.example.shoppingmall.controller;

import com.example.shoppingmall.domain.Member;
import com.example.shoppingmall.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
@Controller
@RequiredArgsConstructor
public class SignController {
    private final LoginService loginService;

    //로그인 페이지
    @GetMapping
    public String login(Model model) {
        model.addAttribute("member", new Member());
        return "login";
    }
    //로그인 요청시 검증후 session에 회원을 저장한다.
    //로그인 후 상품 전체 목록 페이지로 이동
    @PostMapping
    public ResponseEntity login(@ModelAttribute Member member, HttpServletRequest request) throws URISyntaxException {
        loginService.validate(member);
        HttpSession session = request.getSession();
        session.setAttribute("member", loginService.findById(member));
        return ResponseEntity.status(HttpStatus.FOUND).location(new URI("/items")).build();
    }
    //회원 가입 페이지 조회
    @GetMapping("/login/signin")
    public String signin(Model model) {
        model.addAttribute("member", new Member());
        return "login/signin";
    }
    //넘어온 회원 정보를 회원 저장소에 저장한 후 리다이렉트
    @PostMapping("/login/signin")
    public String signup(@ModelAttribute Member member) {
        loginService.save(member);
        return "redirect:..";
    }
    //로그아웃
    //세션을 만료시킨다.
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:";
    }

    //미리 저장된 회원
    @PostConstruct
    public void temp() {
        Member member1 = new Member("전홍영", "jun", "hong");
        Member member2 = new Member("홍길동", "hong", "gil");
        loginService.save(member1);
        loginService.save(member2);
    }
}
