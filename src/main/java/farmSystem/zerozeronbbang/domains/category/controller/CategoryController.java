package farmSystem.zerozeronbbang.domains.category.controller;

import farmSystem.zerozeronbbang.domains.category.dto.ResFindCategoriseDto;
import farmSystem.zerozeronbbang.domains.category.service.impl.CategoryServiceImpl;
import farmSystem.zerozeronbbang.response.CustomResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Category", description = "카테고리 관련 api")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @Operation(summary = "category select", description = "주문 공고 확인시 category 전체 조회 컨트롤러")
    @GetMapping(value = "/categories")
    public CustomResponseEntity<List<ResFindCategoriseDto>> findCategories(){
        List<ResFindCategoriseDto> categories = categoryService.findCategories();
        return CustomResponseEntity.success(categories);
    }
}
