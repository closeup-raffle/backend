package farmSystem.zerozeronbbang.domains;

import farmSystem.zerozeronbbang.domains.menu.Menu;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuOption_id")
    private Long id;

    private String optionName;
    private String optionContent;
    private int optionPrice;

    //메뉴 다대일 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Builder
    public MenuOption(String optionName, String optionContent, int optionPrice){
        this.optionName=optionName;
        this.optionContent=optionContent;
        this.optionPrice = optionPrice;
    }

    //연관관계 메서드 Menu-MenuOption
    public void setMenu(Menu menu){
        this.menu = menu;
        menu.getMenuOptions().add(this);
    }


}
