package 김자경.repository;

import lombok.Data;

//@Data는 위험. @Getter, @Setter만 쓰는게 좋음. 핵심도메인 모델에서는 가능하면 안 사용
//data 왔다갔다용 dto에서는 어느정도 써도 됨
@Data
public class
Item {
    private Long id;
    private String itemName;
    private Integer price; // 가격이 없을 수도 있으니까 Integer
    private Integer quantity; //수량이 없는 경우 있을 수 있으니까 Integer

    // 이거 선언 안 하면 컨트롤러 Item item = new Item(); 오류남
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
