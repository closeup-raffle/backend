package farmSystem.zerozeronbbang.domains.user;

import com.sun.istack.NotNull;
import farmSystem.zerozeronbbang.domains.*;
import farmSystem.zerozeronbbang.domains.orderBoard.OrderBoard;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private String address1; //우편번호
    @NotNull
    private String address2; //지번 혹은 도로명 주소
    @NotNull
    private String address3; //상세주소

//    @Embedded
//    @NotNull
//    private Address address; //카카오 제공 도로명 주소 api 활용?(https://postcode.map.daum.net/guide)

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    //주문 일대다 양방향
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    //주문공고 일대다 양방향
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderBoard> orderBoards = new ArrayList<>();

    //회원카테고리(AI) 일대다 양방향
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<User_Category> user_categories = new ArrayList<>();

    //회원채팅방 일대다 양방향
    @OneToMany(mappedBy = "user")
    private List<User_ChattingRoom> user_chattingRooms = new ArrayList<>();

    //채팅내용 일대다 양방향
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ChattingContent> chattingContents = new ArrayList<>();

    @Builder
    public User(String email, String password, String name, String phone, String address1, String address2, String address3) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
    }

    @Builder
    public User(Long id, String email, String name, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
