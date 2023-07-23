package farmSystem.zerozeronbbang.domains.orderBoard.controller;

import farmSystem.zerozeronbbang.domains.orderBoard.dto.ReqWriteOrderBoardDto;
import farmSystem.zerozeronbbang.domains.orderBoard.dto.ResFindOrderBoardsDto;
import farmSystem.zerozeronbbang.domains.orderBoard.service.OrderBoardServiceImpl;
import farmSystem.zerozeronbbang.response.CustomResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "OrderBoard", description = "주문 공고글 api")
@RequiredArgsConstructor
@RestController
public class OrderBoardController {

    private final OrderBoardServiceImpl orderBoardService;

    @Operation(summary = "orderBoard list", description = "orderBoard 리스트 조회 컨트롤러")
    @GetMapping(value = "/order-boards")
    public CustomResponseEntity<Page<ResFindOrderBoardsDto>> getOrderBoards(@AuthenticationPrincipal final String email, @PageableDefault(size=10, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable){
        Page<ResFindOrderBoardsDto> orderBoards = orderBoardService.findOrderBoards(pageable);
        return CustomResponseEntity.success(orderBoards);
    }

    //@Authen~~에서 email 가져오는 부분이 안됨. ㄴervice에서 user 조횝 부분 null값 뜸
    @Operation(summary = "orderBoard list", description = "orderBoard 리스트 조회 컨트롤러")
    @PostMapping(value = "/order-board")
    public CustomResponseEntity<?> writeOrderBoard(@AuthenticationPrincipal final String email, @RequestBody ReqWriteOrderBoardDto reqWriteOrderBoardDto){
        return CustomResponseEntity.success(orderBoardService.writeOrderBoard(email, reqWriteOrderBoardDto));
    }


}
