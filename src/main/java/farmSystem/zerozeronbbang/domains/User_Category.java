package farmSystem.zerozeronbbang.domains;

import farmSystem.zerozeronbbang.domains.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User_Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_category_id")
    private Long id;

    // 카테고리 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    //회원 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //연관관계 메서드 User-User_Category
    public void setUser(User user){
        this.user=user;
        user.getUser_categories().add(this);
    }

    //연관관계 메서드 Category-User_Category
    public void setCategory(Category category){
        this.category=category;
        category.getUser_categories().add(this);
    }
}
