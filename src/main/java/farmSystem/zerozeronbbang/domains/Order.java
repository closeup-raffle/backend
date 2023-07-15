package farmSystem.zerozeronbbang.domains;

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
@Table(name = "Orders")
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private String storeRequest;

    private String riderRequest;

    //주문 메뉴를 통해 계산
    private int price;

    //해당 엔티티를 결제 엔티티랑 연결지으면 필드로 두지 않아도 될듯?
    private String payment;

    //회원 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //주문공고글 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderBoard_id")
    private OrderBoard orderBoard;

    //주문 메뉴 일대다 양방향
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<Order_Menu> order_menus = new ArrayList<>();

    @Builder
    public Order(String storeRequest, String riderRequest, int price, String payment){
        this.storeRequest = storeRequest;
        this.riderRequest = riderRequest;
        this.price = price;
        this.payment = payment;
    }

    // 연관관계 메서드 User-Order
    public void setUser(User user){
        this.user = user;
        user.getOrders().add(this);
    }

    // 연관관계 메서드 OrderBoard-Order
    public void setOrderBoard(OrderBoard orderBoard){
        this.orderBoard = orderBoard;
        orderBoard.getOrders().add(this);
    }

}
