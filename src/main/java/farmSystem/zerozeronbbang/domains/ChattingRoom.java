package farmSystem.zerozeronbbang.domains;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingRoom extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chattingRoom_id")
    private Long id;

    //유저채팅방 일대다 양방향
    @OneToMany(mappedBy = "chattingRoom", cascade = CascadeType.ALL)
    private List<User_ChattingRoom> user_chattingRooms = new ArrayList<>();

    //주문공고글 일대일 양방향
    @OneToOne
    @JoinColumn(name = "orderBoard_id")
    private OrderBoard orderBoard;

    // 채팅대화 일대다 양방향
    @OneToMany(mappedBy = "chattingRoom", cascade = CascadeType.ALL)
    private List<ChattingContent> chattingContents = new ArrayList<>();


}
