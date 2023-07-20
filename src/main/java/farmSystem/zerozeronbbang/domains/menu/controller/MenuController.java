package farmSystem.zerozeronbbang.domains.menu.controller;

import farmSystem.zerozeronbbang.domains.menu.dto.ReqFindMenuDto;
import farmSystem.zerozeronbbang.domains.menu.dto.ResFindMenuDto;
import farmSystem.zerozeronbbang.domains.menu.service.impl.MenuServiceImpl;
import farmSystem.zerozeronbbang.response.CustomResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Menu", description = "메뉴 관련 api")
@RequiredArgsConstructor
@RestController
public class MenuController {

    private final MenuServiceImpl menuService;

    @Operation(summary = "Menu select", description = "주문 작성 메뉴 선택")
    @GetMapping(value = "/{foodstoreId}/menus")
    public CustomResponseEntity<List<ResFindMenuDto>> findMenus(@PathVariable Long foodstoreId){
        return CustomResponseEntity.success(menuService.findMenus(foodstoreId));
    }
}
