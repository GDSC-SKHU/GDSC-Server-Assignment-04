package com.example.music.controller;

import com.example.music.music.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MusicService musicService;

    @GetMapping("/")
    public String  mainPage(Model model){
        return "home"; //home.html 리턴
    }

}
