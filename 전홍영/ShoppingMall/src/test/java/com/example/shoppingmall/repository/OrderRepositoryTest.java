package com.example.shoppingmall.repository;

import com.example.shoppingmall.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OrderRepositoryTest {
    private final OrderRepository orderRepository;




    @Autowired
    OrderRepositoryTest(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Test
    void repositoryTest() {
        Item item1 = new Item( "전홍영", 1000, 20);
        Item item2 = new Item( "홍길동", 2000, 20);
        //save(), findById() test
        orderRepository.save(item1);
        orderRepository.save(item2);
        Assertions.assertThat(orderRepository.findById(item1.getItemId())).isEqualTo(item1);
        //findAll() test
        List<Item> list = List.of(item1, item2);
        Assertions.assertThat(orderRepository.findAll()).isEqualTo(list);
        //buy() test
        orderRepository.order(item1);
        Assertions
                .assertThat(orderRepository.findById(item1.getItemId())
                .getQuantity())
                .isEqualTo(19);
    }
}