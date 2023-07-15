package farmSystem.zerozeronbbang.domains;

import farmSystem.zerozeronbbang.domains.user.Address;
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
    private Address address;
    private String phone;
    private String minDeliveryPrice;
    private String closedDays;
    private String operationHours;
    private String storePictureUrl;
    private int deliveryTip;

    //메뉴 일대다 양방향
    @OneToMany(mappedBy = "foodStore", cascade = CascadeType.ALL)
    List<Menu> menus = new ArrayList<>();

    //카테고리(음식점 카테고리) 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public FoodStore(String name, Address address, String phone, String minDeliveryPrice, String closedDays, String operationHours, String storePictureUrl, int deliveryTip){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.minDeliveryPrice = minDeliveryPrice;
        this.closedDays = closedDays;
        this.operationHours = operationHours;
        this.storePictureUrl = storePictureUrl;
        this.deliveryTip = deliveryTip;
    }

    //연관관계 메서드 Category-FoodStore
    public void setCategory(Category category){
        this.category = category;
        category.getFoodStores().add(this);
    }
}
