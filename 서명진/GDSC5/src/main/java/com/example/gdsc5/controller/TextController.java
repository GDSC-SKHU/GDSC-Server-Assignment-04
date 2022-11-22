package com.example.gdsc5.controller;


import com.example.gdsc5.member.Member;
import com.example.gdsc5.text.SubText;
import com.example.gdsc5.text.Text;
import com.example.gdsc5.text.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TextController {

    private final TextService textService;


    // 더미 데이터 생성
    @PostConstruct
    public void createdDummyDate(){
        textService.save1(new Text("글쓴이 소개글","이름 : 서명진 \n 생일 : 3월 6일 \n 취미 활동 : 드라마 시청, 게임 "));
        textService.save1(new Text("제목입니다","본문입니다"));
    }

    @GetMapping("/main")
    public String main(Model model,HttpServletRequest request){
        HttpSession session =  request.getSession(false);

        if( session == null || session.getAttribute("sessionUsername") == null){
            // 미인증 사용자 홈 화면으로 강제 이동
            return "redirect:/";
        }
        List<Text> texts = textService.findAll1();
        List<SubText> subTexts = textService.findAll2();
        model.addAttribute("subTexts",subTexts);
        model.addAttribute("texts",texts);
        return "/main";
    }

    @PostMapping("/main")
    public String myToday(@ModelAttribute SubText subText){
        textService.save2(subText);
        return "redirect:/main";
    }

    @GetMapping("/write")
    public String write(Model model){
        Text text = new Text();
        model.addAttribute("text",text);
        return "write";
    }

    @PostMapping("write")
    public String write(@ModelAttribute Text text){
        textService.save1(text);
        return "redirect:/main";
    }




}
