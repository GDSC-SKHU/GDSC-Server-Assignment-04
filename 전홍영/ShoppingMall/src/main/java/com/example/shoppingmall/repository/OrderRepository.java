package com.example.shoppingmall.repository;

import com.example.shoppingmall.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository@Slf4j
public class OrderRepository {
    //상품 저장소
    public static Map<Long, Item> store = new HashMap<>();
    //ItemId 자동 부여
    private static long sequence = 0L;

    //상품 등록
    public Item save(Item item) {
        item.setItemId(++sequence);
        store.put(item.getItemId(), item);
        return item;
    }

    //상품 모두 조회
    public List<Item> findAll() {
        Collection<Item> items = store.values();
        return new ArrayList<>(items);
    }

    //특정 상품 조회
    public Item findById(Long itemId) {
        return store.get(itemId);
    }

    //상품 주문시 상품 수량은 1감소
    //만약 상품이 0이하가 되면 그 상품은 저장소에서 제거
    public void order(Item item) {
        Item item_buy = store.get(item.getItemId());
        if (item_buy.getQuantity() - 1 <= 0) {
            store.remove(item.getItemId());
        } else {
            log.info("item_buy.getQuantity={}", item_buy.getQuantity());
            item_buy.setQuantity(item_buy.getQuantity() - 1);
        }
    }
}
