package farmSystem.zerozeronbbang.domains.orderBoard;

import com.sun.istack.NotNull;
import farmSystem.zerozeronbbang.domains.BaseEntity;
import farmSystem.zerozeronbbang.domains.ChattingRoom;
import farmSystem.zerozeronbbang.domains.foodStore.FoodStore;
import farmSystem.zerozeronbbang.domains.Order;
import farmSystem.zerozeronbbang.domains.user.User;
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
public class OrderBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderBoard_id")
    private Long id;
    private String title;


    @NotNull
    private String endTime;

    @NotNull
    private int numOfRecruit;

    //수령 위치
    private String address1;
    private String address2;
    private String address3;
    private boolean completed;

    private String storeRequest;

    private String riderRequest;

    //회원 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //주문 일대다 양방향
    @OneToMany(mappedBy = "orderBoard", cascade = CascadeType.ALL)
    List<Order> orders = new ArrayList<>();

    //음식점 다대일 단방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodStore_id")
    private FoodStore foodStore;

    // 채팅방 일대일 양방향
    @OneToOne(mappedBy = "orderBoard", cascade = CascadeType.ALL)
    private ChattingRoom chattingRoom;

    @Builder
    public OrderBoard(String endTime, int numOfRecruit, String address1,String address2,String address3, boolean completed, String storeRequest, String riderRequest, String title){
        this.endTime = endTime;
        this.numOfRecruit = numOfRecruit;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.completed = completed;
        this.storeRequest = storeRequest;
        this.riderRequest = riderRequest;
        this.title = title;
    }

    // 연관관계 메서드 User-OrderBoard
    public void setUser(User user){
        this.user = user;
        user.getOrderBoards().add(this);
    }

    public void setFoodStore(FoodStore foodStore){
        this.foodStore=foodStore;
    }
}
