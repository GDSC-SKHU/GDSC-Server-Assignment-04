package com.example.springintroduction.controller;

import com.example.springintroduction.sugang.SugangService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final SugangService sugangService;


    @GetMapping("/")
    public String mainPage(Model model) {
        return "home"; // home html
    }


}