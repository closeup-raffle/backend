package farmSystem.zerozeronbbang.domains;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private String menuPhotoUrl;

    private int price;

    private String menuExplanation;
// 카테고리는 좀더 고민해보자
//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
//    private List<Menu_Category> menu_categories = new ArrayList<>();

    //메뉴 옵션 일대다 양방향
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    List<MenuOption> menuOptions = new ArrayList<>();

    //음식점 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodStore_id")
    private FoodStore foodStore;

    @Builder
    public Menu(String menuPhotoUrl, String menuExplanation, int price){
        this.menuPhotoUrl = menuPhotoUrl;
        this.menuExplanation=menuExplanation;
        this.price = price;
    }

    // 연관관계 메서드 Menu-FoodStore
    public void setFoodStore(FoodStore foodStore){
        this.foodStore = foodStore;
        foodStore.getMenus().add(this);
    }
}
