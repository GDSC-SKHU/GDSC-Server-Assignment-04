package com.example.springintroduction.controller;

import com.example.springintroduction.sugang.Sugang;
import com.example.springintroduction.sugang.SugangService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 강의 관리와 강의 조회를 할 수 있는 컨트롤러
@Controller
@RequiredArgsConstructor
public class SugangController {

    private final SugangService sugangService;

    // 강의 추가
    @GetMapping("/addsugang")
    public String addsugangPage(Model model) {
        model.addAttribute(new Sugang());
        return "addsugang";
    }

    // 강의 추가
    @PostMapping("/addsugang")
    public String addsugangPage(@ModelAttribute Sugang sugang) {
        sugangService.save(sugang);
        return "redirect:/#addsugang=ok";
    }
    // 강의 목록 조회
    @GetMapping("/search")
    public String sugangSearch(Model model, @RequestParam(required = false) String query) {
        if (query == null) {
            List<Sugang> sugangs = sugangService.findAll();
            model.addAttribute("sugangs", sugangs);

            return "search";
        }
        List<Sugang> sugangs = sugangService.findBysugangname(query);
        model.addAttribute("sugangs", sugangs);
        return "search";
    }
    //강의 삭제
    @GetMapping("search/delete/{sugangname}")
    public String deletesugang(@PathVariable String sugangname) {
        sugangService.delete(sugangname);
        return "redirect:/search";
    }


}