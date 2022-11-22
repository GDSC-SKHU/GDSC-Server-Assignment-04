package 김자경.controller;

import 김자경.repository.Item;
import 김자경.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    // 생성자가 1개만 있으면 Autowired 생략 가능
    // @RequiredArgsConstructor를 사용하면 final 필드가 있는 itemRepository를 자동 생성

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    // 상픔 상세 컨트롤러
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // 상품 등록 폼으로 이동하기 위한 컨트롤러
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    // 상품 등록 처리를 위한 컨트롤러
    public String addItemV1(@RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity, Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // ModelAttribute("네임")의 네임은 model.addAttribute 역할을 해줌
    // view에서 보여줄 attributeName이라고 볼 수 있음
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        // model.addAttribute("item", item); -> ModelAttribute("item")으로 인해 생략 가능
        return "basic/item";
    }

    // ModelAttribute(네임)의 '네임'생략시 클래스네임 앞을 소문자로 바꿔서 model.addAttribute(name,value)가 됨
    // ex) ModelAttribute Item item = model.addAttribute("item", value)
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        //model.addAttribute("item", item); //ModelAttribute("item")으로 인해 생략 가능

        return "basic/item";
    }

    // ModelAttribute 생략 가능 -> 단순타입인 경우(ex.String) 생략하면 @RequestParam
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    // PRG 패턴 적용 -> 저장로직 중복 방지
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    // redirect 후 model 처리할 수 있는 방법
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);

        // {itemId}에 itemId가 들어감
        // status는 쿼리파라미터 형태로 들어감
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

    // 상품 수정 폼
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    // 상품 수정
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        // 뷰템플릿 호출 대신 상품상세로 리다이렉트(302)
        // @PathVariable 값 사용 가능
        return "redirect:/basic/items/{itemId}";
    }

    // 데이터 삽입
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("서울우유", 2890, 6));
        itemRepository.save(new Item("매일우유", 2960, 4));

    }
}
