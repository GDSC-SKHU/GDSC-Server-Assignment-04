package com.example.gdscspringhw.controller;

import com.example.gdscspringhw.member.Member;
import com.example.gdscspringhw.member.MemberService;
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

    @GetMapping("/")
    public String mainPage(Model model, @RequestParam(required = false) String query) {
        // 네이버 검색창에 아이디를 넣고 검색하면 넣은 아이디가 query로 넘어와서 작동함
        // premium을 입력하고 검색하면 구독 여부를 체크해서 구독자만 보이게 함
        // query 가 비었으면 전체 조회
        if(query == null) {
            List<Member> members = memberService.findAll();
            model.addAttribute("members", members);

            return "home";
        }
        // query 가 premium 이라면 프리미엄 구독 회원만 조회
        else if (query.equals("premium")) {
            // 프리미엄 구독 회원 객체들만 넘어옴
            List<Member> members = memberService.findByPremium();
            model.addAttribute("members", members);

            return "home";
        }

        // 아니면 단건 조회
        List<Member> members = memberService.findByUsername(query);
        model.addAttribute("members", members);
        return "home";
    }

    // 더미 데이터 생성, (아이디, 비밀번호, 네이버 premium 구독 여부)
    @PostConstruct
    public void createData() {
        memberService.save(new Member("상남자", "1234", true));
        memberService.save(new Member("중남자", "1234", false));
        memberService.save(new Member("하남자", "1234", false));
    }

}
