package farmSystem.zerozeronbbang.domains;

import farmSystem.zerozeronbbang.domains.category.Category;
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
public class FoodStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodStore_id")
    private Long id;

    private String name;
    private String address1;
    private String address2;
    private String address3;
    private String phone;
    private int minDeliveryPrice;
    private String closedDays;
    private String operationHours;
    private String storePictureUrl;
    private int deliveryTip;
    private int deliveryTime;

    //메뉴 일대다 양방향
    @OneToMany(mappedBy = "foodStore", cascade = CascadeType.ALL)
    List<Menu> menus = new ArrayList<>();

    //카테고리(음식점 카테고리) 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public FoodStore(String name, String address1, String address2, String address3, String phone, int minDeliveryPrice, String closedDays, String operationHours, String storePictureUrl, int deliveryTip, int deliveryTime){
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.phone = phone;
        this.minDeliveryPrice = minDeliveryPrice;
        this.closedDays = closedDays;
        this.operationHours = operationHours;
        this.storePictureUrl = storePictureUrl;
        this.deliveryTip = deliveryTip;
        this.deliveryTime = deliveryTime;
    }

    //연관관계 메서드 Category-FoodStore
    public void setCategory(Category category){
        this.category = category;
        category.getFoodStores().add(this);
    }
}
