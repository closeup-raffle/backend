package farmSystem.zerozeronbbang.domain;

import farmSystem.zerozeronbbang.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chattingContent_id")
    private Long id;

    private String content;

    private LocalDateTime sendTime;

    //회원 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //채팅방 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chattingRoom_id")
    private ChattingRoom chattingRoom;

    @Builder
    public ChattingContent(String content){
        this.content=content;
        this.sendTime=LocalDateTime.now();
    }

    //연관관계 메서드 User-ChattingContent
    public void setUser(User user){
        this.user=user;
        user.getChattingContents().add(this);
    }

    //연관관계 메서드 ChattingRoom-ChattingContent
    public void setChattingRoom(ChattingRoom chattingRoom){
        this.chattingRoom=chattingRoom;
        chattingRoom.getChattingContents().add(this);
    }
}
