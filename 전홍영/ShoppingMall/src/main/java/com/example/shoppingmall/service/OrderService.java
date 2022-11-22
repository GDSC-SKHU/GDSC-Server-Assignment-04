package com.example.shoppingmall.service;

import com.example.shoppingmall.domain.Item;
import com.example.shoppingmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    //상품 등록
    public Item save(Item item) {
        return orderRepository.save(item);
    }

    //상품 조회
    public Item findById(Long itemId) {
        return orderRepository.findById(itemId);
    }

    //상품 구매
    public void order(Item item) {
        orderRepository.order(item);
    }

    //상품 모두 조회
    public List<Item> findAll() {
        return orderRepository.findAll();
    }

}
