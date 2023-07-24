package farmSystem.zerozeronbbang.domains.foodStore.controller;

import farmSystem.zerozeronbbang.domains.foodStore.dto.ResFindFoodStoreDto;
import farmSystem.zerozeronbbang.domains.foodStore.service.impl.FoodStoreServiceImpl;
import farmSystem.zerozeronbbang.response.CustomResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "FoodStore", description = "음식점 관련 api")
@RequiredArgsConstructor
@RestController
public class FoodStoreController {

    private final FoodStoreServiceImpl foodStoreService;

    //무한 스크롤 사용시 slice로 페이징 처리(이후에 의논하고 결정)
    @Operation(summary = "FoodStore select", description = "카테고리별 음식점 조회 api")
    @GetMapping(value = "/food-stores/{category-id}")
    public CustomResponseEntity<List<ResFindFoodStoreDto>> findFoodStores(@PathVariable(name = "category-id") Long categoryId){
        return CustomResponseEntity.success(foodStoreService.findFoodStores(categoryId));
    }

}
