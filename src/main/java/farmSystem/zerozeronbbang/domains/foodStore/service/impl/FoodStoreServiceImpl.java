package farmSystem.zerozeronbbang.domains.foodStore.service.impl;

import farmSystem.zerozeronbbang.domains.foodStore.FoodStore;
import farmSystem.zerozeronbbang.domains.foodStore.dto.ReqFindFoodStoreDto;
import farmSystem.zerozeronbbang.domains.foodStore.dto.ResFindFoodStoreDto;
import farmSystem.zerozeronbbang.domains.foodStore.repository.FoodStoreRepository;
import farmSystem.zerozeronbbang.domains.foodStore.service.FoodStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodStoreServiceImpl implements FoodStoreService {

    private final FoodStoreRepository foodStoreRepository;


    // 휴무일인지, 운영 시간인지 파악하는 로직 필요
    @Override
    @Transactional(readOnly = true)
    public List<ResFindFoodStoreDto> findFoodStores(ReqFindFoodStoreDto reqFindFoodStoreDto) {
        List<FoodStore> foodStores = foodStoreRepository.findByCategoryId(reqFindFoodStoreDto.getCategoryId());
        return foodStores.stream()
                .map(foodStore -> ResFindFoodStoreDto.builder()
                        .id(foodStore.getId())
                        .name(foodStore.getName())
                        .minDeliveryPrice(foodStore.getMinDeliveryPrice())
                        .storePictureUrl(foodStore.getStorePictureUrl())
                        .deliveryTip(foodStore.getDeliveryTip())
                        .deliveryTime(foodStore.getDeliveryTime())
                        .build())
                .collect(Collectors.toList());
    }
}
