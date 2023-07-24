package farmSystem.zerozeronbbang.domains.orderBoard.controller;

import farmSystem.zerozeronbbang.domains.orderBoard.dto.ReqWriteOrderBoardDto;
import farmSystem.zerozeronbbang.domains.orderBoard.dto.ResFindOrderBoardDto;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "OrderBoard", description = "주문 공고글 api")
@RequiredArgsConstructor
@RestController
public class OrderBoardController {

    private final OrderBoardServiceImpl orderBoardService;

    @Operation(summary = "orderBoard list", description = "orderBoard 리스트 조회 컨트롤러")
    @GetMapping(value = "/order-boards")
    public CustomResponseEntity<Page<ResFindOrderBoardsDto>> getOrderBoards(@PageableDefault(size=10, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable){
        Page<ResFindOrderBoardsDto> orderBoards = orderBoardService.findOrderBoards(pageable);
        return CustomResponseEntity.success(orderBoards);
    }

    //@Authen~~에서 userdetails가 정의되어 잇지 않아서 정보를 가져오지 못함.
    @Operation(summary = "orderBoard list", description = "orderBoard 작성 컨트롤러")
    @PostMapping(value = "/order-boards")
    public CustomResponseEntity<?> writeOrderBoard(@AuthenticationPrincipal final String email, @RequestBody ReqWriteOrderBoardDto reqWriteOrderBoardDto){
        return CustomResponseEntity.success(orderBoardService.writeOrderBoard(email, reqWriteOrderBoardDto));
    }

    @Operation(summary = "orderBoard list", description = "orderBoard 상세 조회(단건) 컨트롤러")
    @GetMapping(value = "/order-boards/{order-board-id}")
    public CustomResponseEntity<ResFindOrderBoardDto> getOrderBoard(@PathVariable(name = "order-board-id") Long orderBoardId){
        return CustomResponseEntity.success(orderBoardService.findOrderBoard(orderBoardId));
    }


}
