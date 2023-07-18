package farmSystem.zerozeronbbang.domains.foodStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ResFindFoodStoreDto {
    private Long id;
    private String name;
    private int minDeliveryPrice;
    private String storePictureUrl;
    private int deliveryTip;
    private int deliveryTime;
}
