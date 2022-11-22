package com.example.hyunbok.controller;

import com.example.hyunbok.member.Member;
import com.example.hyunbok.member.MemberService;
import com.example.hyunbok.subject.Subject;
import com.example.hyunbok.subject.SubjectService;
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

    private final SubjectService subjectService;
    private final MemberService memberService;

    @GetMapping("/")
    public String mainPage(Model model, @RequestParam(required = false) String query){
        List<Subject> subjects = subjectService.findAll();

        if(query != null)
            subjects = subjectService.findBySubName(query);


        model.addAttribute("subjects",subjects);
        Member member = new Member();
        model.addAttribute("member",member);
        model.addAttribute("error","");
        return "home";
    }

    @PostConstruct
    public void CreateSubject(){
        subjectService.save(new Subject("XB00008","웹프로그래밍","소프트웨어공학과"));
        subjectService.save(new Subject("XC00024","C++프로그래밍","정보통신공학과"));
        subjectService.save(new Subject("XB00015","프론트엔드 개발","소프트웨어공학과"));
        subjectService.save(new Subject("XA00037","Node.js프로그래밍","컴퓨터공학과"));
    }

    @PostConstruct
    public void createDummyData(){
        memberService.save(new Member("test","1234"));
        memberService.save(new Member("testing","1234"));
    }

}
