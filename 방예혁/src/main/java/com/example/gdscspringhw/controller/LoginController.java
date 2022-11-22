package com.example.gdscspringhw.controller;

import com.example.gdscspringhw.member.Member;
import com.example.gdscspringhw.member.MemberService;
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
    public String signInPage(Model model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "signin";
    }

    @PostMapping("signin")
    public ResponseEntity signIn(@ModelAttribute Member member, HttpServletRequest request) throws URISyntaxException {
        // 비밀번호 검사
        memberService.validate(member);

        // 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute("sessionUsername", member.getUsername());

        // 로그인한 회원의 구독 여부를 알아내어 세션에 저장
        session.setAttribute("sessionPremium", memberService.checkPremium(member));
        return ResponseEntity.status(HttpStatus.FOUND).location(new URI("/")).build();

        // return "redirect:/";
    }

    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute(new Member()); // model.addAttribute("member", new Member()); 와 같음, 자동으로 "member"로 해줌
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute @Valid Member member, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "signup";
        }

        memberService.save(member);
        return "redirect:/#signup=ok";
    }

    @PostMapping("/signout")
    public String signOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/joinPremium")
    public String joinPremiumPage(Model model) {
        model.addAttribute(new Member()); // model.addAttribute("member", new Member()); 와 같음, 자동으로 "member"로 해줌
        return "joinPremium";
    }

    @PostMapping("/joinPremium")
    public String joinPremium(HttpSession session) {
        // 세션에 저장된 username과 findByUsername을 이용해서 현재 로그인된 member 객체를 얻은 후 joinPremium 실행
        String username = (String)session.getAttribute("sessionUsername");
        Member member = memberService.findByUsername(username).get(0);
        memberService.joinPremium(member);
        // sessionPremium 값을 기준으로 premium 서비스가 보여지기 때문에 바뀐 값으로 적용되도록 업데이트 해줌
        session.setAttribute("sessionPremium", memberService.checkPremium(member));
        return "redirect:/";
    }

    @GetMapping("/forPremiumMembers")
    public String forPremiumMembers(HttpServletRequest request) {

        // 파라미터 false로 하면, 기존 세션이 유효할 경우만 해당 세션을 가져옴
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("sessionUsername") == null) {
            // 미인증 사용자 홈 화면으로 강제 이동
            return "redirect:/";
        }
        if((!(Boolean)session.getAttribute("sessionPremium"))) {
            // 프리미엄 구독자가 아니면 홈 화면으로 강제 이동
            return "redirect:/";
        }
        return "forPremiumMembers";
    }
}
