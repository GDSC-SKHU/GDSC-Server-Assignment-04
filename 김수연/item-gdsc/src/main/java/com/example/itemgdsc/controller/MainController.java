package com.example.itemgdsc.controller;

import com.example.itemgdsc.Member.Member;
import com.example.itemgdsc.Member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

// 메인화면의 자잘한 기능들을 수행하는 컨트롤러
@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;
    // 스프링은 아래처럼 실체(객체)를 넣는 수고를 덜어 준다.
    //private final MemberService memberService = new MemberService(new MemberRepository(), new BCryptPasswordEncoder());

    // 생성자
    //    public MainController(MemberService memberService) {
    //        this.memberService = memberService;
    //    }
    /**
     * @RequestParam
     *  in Spring MVC, "request parameters" map to query parameters, form data, and parts in multipart requests
     *  쿼리 파라미터와 폼 데이터를 받는다.
     *
     *  예시에서는 url에 적힌 쿼리 스트링으로 값을 전달했지만, html form 태그를 이용한 값 전달도 가능하다.
     * */
    // 해당 path의 Get 요청 시 실행
    @GetMapping("/")
    public String mainPage(Model model, @RequestParam(required = false) String query){

        if( query == null ){
            List<Member> members = memberService.findAll();
            model.addAttribute("members", members);

            //model.addAttribute(members); // attributeName memberList
            return "home"; // templates 디렉토리에서 home.html을 찾는다.
        }
        List<Member> members = memberService.findByUsername(query);
        model.addAttribute("members", members);
        return "home";
    }
}
