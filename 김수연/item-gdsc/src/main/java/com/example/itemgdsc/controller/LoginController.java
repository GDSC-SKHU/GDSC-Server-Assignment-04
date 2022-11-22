package com.example.itemgdsc.controller;

import com.example.itemgdsc.Member.Member;
import com.example.itemgdsc.Member.MemberService;
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

// 회원가입과 인증을 맡은 컨트롤러

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    // 해당 path의 Get 요청 시 실행
    @GetMapping("/signin")
    public String signInPage(Model model) {
        //Member member = new Member();
        //model.addAttribute("member", member);
        /* 위 주석과 아래 한 줄의 코드는 같은 효과
         *  signin.html 파일에 있는 th:value="{}" 에서, Model에 담긴 값을 기반으로 html 텍스트를 표시하도록
         *  하고 있기 때문에, 빈 Member객체라도 Model에 담아 보내지 않으면 오류가 발생
         * */
        model.addAttribute(new Member());
        return "signin";
    }
    // 해당 path의 POST 요청 시 실행
    /**
     * // @ModelAttribute는 자동으로 Model.addAttribute(member) 실행한다.
     * //  @ModelAttribute는 getter와 setter를 이용해 사용자 요청값을 바인딩한다.
     */
    @PostMapping("/signin")
    public ResponseEntity signIn(@ModelAttribute Member member, HttpServletRequest request) throws URISyntaxException {
        // 로그인 시도한 데이터를 기반으로 비밀번호 검사
        memberService.validate(member);
        HttpSession session = request.getSession();

        // 세션을 생성한다. 응답에 자동으로 JSESSIONID 쿠키를 보낸다.
        session.setAttribute("sessionUsername", member.getUsername());
        return ResponseEntity.status(HttpStatus.FOUND).location(new URI("/products/items")).build();
    } //로그인 성공하면 상품등록 첫 화면으로 이동

    @GetMapping("/signup")
    public String signUpPage(Model model) {

        model.addAttribute(new Member());
        return "signup";
    }

    @PostMapping("/signup")
    // @ModelAttribute는 자동으로 Model.addAttribute(member) 실행
    // 클래스에 @Valid 어노테이션 적용시키고, 필드에 제약조건 달아 검증하기
    public String signUp(@ModelAttribute @Valid Member member, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signup";
        }
        memberService.save(member);
        return "redirect:/#signup=ok"; // 회원가입 성공 시 -> http://localhost:8080/#signup=ok
    }
    @PostMapping("/products/signout")
    public String signOut(HttpServletRequest request) {
        // 세션 만료
        request.getSession().invalidate();
        return "redirect:/";
    }
}

