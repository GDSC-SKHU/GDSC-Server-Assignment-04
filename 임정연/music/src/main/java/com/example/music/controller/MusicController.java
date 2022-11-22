package com.example.music.controller;

import com.example.music.music.Music;
import com.example.music.music.MusicRepository;
import com.example.music.music.MusicService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping
    public String musics(Model model){
        List<Music> musics= MusicRepository.findAll();
        model.addAttribute("musics",musics);
        return "musics";
    }

    @GetMapping("/addMusic")
    private String addMusicPage(Model model){
        model.addAttribute(new Music());
        return "addMusic";
    }

    @PostMapping("/addMusic")
    private String addMusicPage(Music music, RedirectAttributes redirectAttributes){
        Music savedMusic  = MusicRepository.save(music);
        musicService.save(music);
        redirectAttributes.addAttribute("musics",savedMusic.getTitle());
        return "redirect:/#addmusic=ok";
    }

    @GetMapping("/search")
    public String musicSearch(Model model, @RequestParam(required = false)String query){
        if(query==null){
            List<Music> musics = musicService.findAll();
            model.addAttribute("music",musics);

            return"search";
        }
        List<Music> musics = musicService.findBytitle(query);
        model.addAttribute("music",musics);
        return"search";
    }

    @GetMapping("search/delete.{title}")
    public String deleteMusic(@PathVariable String title){
        musicService.delete(title);
        return "redirect:/music";
    }

}
