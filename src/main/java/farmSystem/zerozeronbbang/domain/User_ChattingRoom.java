package farmSystem.zerozeronbbang.domain;

import farmSystem.zerozeronbbang.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User_ChattingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_chattingRoom_id")
    private Long id;

    //회원 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //채팅방 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chattingRoom_id")
    private ChattingRoom chattingRoom;

    //연관관계 메서드 User-User_ChattingRoom
    public void setUser(User user){
        this.user=user;
        user.getUser_chattingRooms().add(this);
    }

    //연관관계 메서드 ChattingRoom-User_ChattingRoom
    public void setChattingRoom(ChattingRoom chattingRoom){
        this.chattingRoom = chattingRoom;
        chattingRoom.getUser_chattingRooms().add(this);
    }
}
