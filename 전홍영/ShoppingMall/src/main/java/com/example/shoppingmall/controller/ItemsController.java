package com.example.shoppingmall.controller;

import com.example.shoppingmall.domain.Item;
import com.example.shoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemsController {
    private final OrderService orderService;
    //전체 상품 조회하여 view에 전달
    @GetMapping()
    public String items(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        model.addAttribute("items", orderService.findAll());
        return "items";
    }
    //상품 조회 view GET
    @GetMapping("/{itemId}")
    public String items_item(@PathVariable Long itemId, Model model) {
        Item item = orderService.findById(itemId);
        model.addAttribute("item", item);
        return "items/item";
    }
    //상품 등록 view GET
    @GetMapping("/addItem")
    public String addItem(Model model) {
        model.addAttribute(new Item());
        return "items/addItem";
    }
    //상품 등록 POST
    //입력한 상품 저장소에 저장
    @PostMapping("/addItem")
    public String addItem(@ModelAttribute("item") Item item) {
        orderService.save(item);
        return "redirect:";
    }
    //미리 저장한 상품
    @PostConstruct
    public void temp() {
        Item eclipse = new Item( "이클립스", 2000, 100);
        Item snack = new Item( "빼빼로", 1500, 100);
        orderService.save(eclipse);
        orderService.save(snack);
    }

}
