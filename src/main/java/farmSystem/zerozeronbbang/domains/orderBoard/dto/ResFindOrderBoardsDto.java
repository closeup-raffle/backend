package farmSystem.zerozeronbbang.domains.orderBoard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//주문 공고 전체 조회 응답
public class ResFindOrderBoardsDto {

    private Long id;
    private String endTime;
    private int numOfRecruit;
    private boolean completed;
    private String foodStoreName;
    private String storePictureUrl;
    private int minDeliveryPrice;
    private int deliveryTip;
    private int deliveryTime;
    private LocalDateTime createdAt;
}
