package farmSystem.zerozeronbbang.domains.foodStore.service;

import farmSystem.zerozeronbbang.domains.foodStore.dto.ReqFindFoodStoreDto;
import farmSystem.zerozeronbbang.domains.foodStore.dto.ResFindFoodStoreDto;

import java.util.List;

public interface FoodStoreService {
    List<ResFindFoodStoreDto> findFoodStores(Long categoryId);
}
