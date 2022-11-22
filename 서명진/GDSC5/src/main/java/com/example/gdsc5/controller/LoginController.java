package com.example.gdsc5.controller;

import com.example.gdsc5.member.Member;
import com.example.gdsc5.member.MemberService;
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
        model.addAttribute("member",member);
        return "signin";
    }

    @PostMapping("/signin")
    public ResponseEntity signIn(@ModelAttribute Member member, HttpServletRequest request) throws URISyntaxException {
        // 비밀번호 검사
        memberService.validate(member);
        // 세션 생성
        HttpSession session =  request.getSession();
        session.setAttribute("sessionUsername",member.getUsername());
        return ResponseEntity.status(HttpStatus.FOUND).location(new URI("/")).build();

    }

    @GetMapping("/signup")
    public String signUpPage(Model model){
        model.addAttribute(new Member());   // member
//        model.addAttribute("member", new Member());
        return "signup";
    }

    @PostMapping("signup")
    public String signUp(@ModelAttribute @Valid Member member, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup";
        }

        memberService.save(member);
        return "redirect:/#signup=ok";      //# 은 없어도 되나 보기 편하기위해 해놓은거
    }

    @PostMapping("/signout")
    public String signOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }
}
