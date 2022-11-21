package com.example.itemgdsc.web.basic;


import com.example.itemgdsc.domain.item.Item;
import com.example.itemgdsc.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products/items")
@RequiredArgsConstructor
public class ProductsItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items=itemRepository.findAll();//모든 item출력
        model.addAttribute("items",items);
        return "products/items";
    }
    //URI에서 아래 1234 부분이 @PathVariable로 처리
    //http://localhost:8080/api/user/1234
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId); //PathVariable 로 넘어온 상품ID로 상품을 조회하고
        model.addAttribute("item", item);//모델에 담아두고,
        return "products/item";//뷰 템플릿을 호출한다.
    }

    @GetMapping("/add") //상품등록
    public String addForm() {
        return "products/addForm";
    }

   @PostMapping("/add") //RedirectAttributes 를 사용하면 URL 인코딩을 해준다
                        //@PathVariable, 쿼리 파라미터까지 처리해준다
    public String addItem(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        return "redirect:/products/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit") //itemId 상품 수정
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "products/editForm";
    }

    @PostMapping("/{itemId}/edit")//수정한 id값/edit 페이지
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item); //수정한 item 값 update
        return "redirect:/products/items/{itemId}";
    }

    @GetMapping("/delete/{itemId}")//상품 삭제 기능
    public String deleteItem(@PathVariable Long itemId) {
        itemRepository.delete(itemId);
        return "redirect:/products/items";
    }
}