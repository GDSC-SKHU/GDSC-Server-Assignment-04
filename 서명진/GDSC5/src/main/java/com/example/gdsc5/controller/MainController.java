package com.example.gdsc5.controller;

import com.example.gdsc5.member.Member;
import com.example.gdsc5.member.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Queue;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    @GetMapping("/")
    public String mainPage(Model model, @RequestParam(required = false) String query){

        if(query == null){
            List<Member> members = memberService.findAll();
            model.addAttribute("members",members);
            return "home";
        }

        List<Member> members = memberService.findByUsername(query);
        model.addAttribute("members",members);
        return "home";
    }

    // 더미 데이터 생성
    @PostConstruct
    public void createdDummyDate(){
        memberService.save(new Member("상남자","1234"));
        memberService.save(new Member("서명진","1234"));
    }

    @GetMapping("/formembers")
    public String forMembers(HttpServletRequest request){

        // 파라미터 false로 하면, 기존 세션이 유효할 경우만 해당 세션을 가져옴
        HttpSession session =  request.getSession(false);

        if( session == null || session.getAttribute("sessionUsername") == null){
            // 미인증 사용자 홈 화면으로 강제 이동
            return "redirect:/";
        }

        return  "formembers";
    }
}
