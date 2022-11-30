package com.gdsc.gdsc_hw_2_2.controller;

import com.gdsc.gdsc_hw_2_2.dto.Hope;
import com.gdsc.gdsc_hw_2_2.dto.Library;
import com.gdsc.gdsc_hw_2_2.mapper.LibraryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequiredArgsConstructor 의존성 주입의 편리성
@Controller
public class LibraryController {

    @Autowired
    LibraryMapper libraryMapper;

    //시작하자마자 책 list출력
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("libraries", libraryMapper.findAll());
        return "list";
    }

    //id로 검색한 후에 그 상세페이지로 연결 시키기
    @RequestMapping("/detail")
    public String detail(Model model, Integer id) {
        model.addAttribute("library", libraryMapper.findById(id));
        return "detail";
    }

    //희망도서 입력폼 연결
    @GetMapping("/signup")
    public String form(Model model) {
        Hope hope = new Hope();
        model.addAttribute("hope", hope);
        return "signup";
    }

    @PostMapping("/signup")
    public String form(Model model, Hope hope) {
        libraryMapper.insert(hope);
        return "redirect:list";
    }

    @GetMapping("/signList")
    public String sign(Model model) {
        model.addAttribute("hopes", libraryMapper.findByAll());
        return "signList";
    }

}
