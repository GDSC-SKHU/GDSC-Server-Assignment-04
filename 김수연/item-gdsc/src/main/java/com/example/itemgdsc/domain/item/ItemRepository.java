package com.example.itemgdsc.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private  static final Map<Long,Item> store=new HashMap<>();
    private static long sequence=0L;

    public Item save(Item item){
        item.setId(++sequence);//item들어올 때마다 id값 증가
        store.put(item.getId(),item);//item넣어주고
        return item;//item반환
    }

    public Item findById(Long id){
        return store.get(id);
    }
    public List<Item> findAll()//item전체 조회
    {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId,Item updateParam){
        Item findItem = findById(itemId); //itemId로 아이템을 찾고
        findItem.setItemName(updateParam.getItemName());//파라미터 정보가 넘어옴, 이름
        findItem.setPrice(updateParam.getPrice());//가격
        findItem.setQuantity(updateParam.getQuantity());//수량 조회
    }

    public void clearStore(){ // HashMap데이터 다 날라가도록 설정
        store.clear();
    }
    public void delete(Long itemId) {
        store.remove(itemId);
    }
}

