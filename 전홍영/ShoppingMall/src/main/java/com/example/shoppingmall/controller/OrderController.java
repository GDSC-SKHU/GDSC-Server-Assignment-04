package com.example.shoppingmall.controller;

import com.example.shoppingmall.domain.Item;
import com.example.shoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    OrderService orderService;
    //특정 상품 구매
    //@PathVariable로 넘어온 ID로 상품 조회한 후 주문
    @GetMapping("/{itemId}")
    public String order(@PathVariable String itemId, Model model) {
        Item item = orderService.findById(Long.parseLong(itemId));
        orderService.order(item);
        model.addAttribute("item", item);
        return "order/item";
    }
}
