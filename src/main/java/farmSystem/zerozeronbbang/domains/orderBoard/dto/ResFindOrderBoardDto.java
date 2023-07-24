package farmSystem.zerozeronbbang.domains.orderBoard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 주문 공고 상세 조회 응답
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResFindOrderBoardDto {

    private Long id;
    private String endTime;
    private int numOfRecruit;
    //배달 수령 위치
    private String address1;
    private String address2;
    private String address3;
    private boolean completed;
    private String foodStoreName;
    private String storePictureUrl;
    private int minDeliveryPrice;
    private int deliveryTip;
    private int deliveryTime;
    private LocalDateTime createdAt;

}
