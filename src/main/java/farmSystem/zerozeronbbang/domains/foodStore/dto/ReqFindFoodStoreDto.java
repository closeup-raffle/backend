package farmSystem.zerozeronbbang.domains.foodStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReqFindFoodStoreDto {
    private Long categoryId;
}
