package com.example.hyunbok.controller;

import com.example.hyunbok.subject.Subject;
import com.example.hyunbok.subject.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class AddController {
    private final SubjectService subjectService;

    @GetMapping("/subAdd")
    public String subAddPage(Model model){
        Subject subject = new Subject();
        model.addAttribute("subject",subject);
        return "subAdd";
    }

    @PostMapping("/subAdd")
    public String subAdd(@ModelAttribute @Valid Subject subject, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "subAdd";
        }
        subjectService.save(subject);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String subDelete(@ModelAttribute Subject subject, Model model){
        subjectService.deleteBySubject(subject.getName());

        return "redirect:/";
    }
}
