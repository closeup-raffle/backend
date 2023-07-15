package farmSystem.zerozeronbbang.domains;

import com.sun.istack.NotNull;
import farmSystem.zerozeronbbang.domains.user.Address;
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
public class OrderBoard extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderBoard_id")
    private Long id;

    @NotNull
    private String endTime;

    @NotNull
    private int numOfRecruit;

    @NotNull
    private Address pickUpAddress;

    private boolean completed;

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
    public OrderBoard(String endTime, int numOfRecruit, Address pickUpAddress, boolean completed){
        this.endTime = endTime;
        this.numOfRecruit = numOfRecruit;
        this.pickUpAddress = pickUpAddress;
        this.completed = completed;
    }

    // 연관관계 메서드 User-OrderBoard
    public void setUser(User user){
        this.user = user;
        user.getOrderBoards().add(this);
    }
}
