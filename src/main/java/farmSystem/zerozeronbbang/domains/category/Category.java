package farmSystem.zerozeronbbang.domains.category;

import farmSystem.zerozeronbbang.domains.foodStore.FoodStore;
import farmSystem.zerozeronbbang.domains.User_Category;
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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private int categoryCode;

    private String categoryName;

    // 음식점 일대다 양방향
    @OneToMany(mappedBy = "category")
    private List<FoodStore> foodStores = new ArrayList<>();

    // 유저_카테고리 일대다 양방향
    @OneToMany(mappedBy = "category")
    private List<User_Category> user_categories = new ArrayList<>();

    @Builder
    public Category(String categoryName, int categoryCode){
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }

}
