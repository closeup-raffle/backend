package farmSystem.zerozeronbbang.domains.orderBoard.service;

import farmSystem.zerozeronbbang.domains.orderBoard.dto.ReqWriteOrderBoardDto;
import farmSystem.zerozeronbbang.domains.orderBoard.dto.ResFindOrderBoardsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderBoardService {

    Page<ResFindOrderBoardsDto> findOrderBoards(Pageable pageable);

    ReqWriteOrderBoardDto writeOrderBoard(String email, ReqWriteOrderBoardDto reqWriteOrderBoardDto);
}
