package farmSystem.zerozeronbbang.domains.orderBoard.service;

import farmSystem.zerozeronbbang.domains.foodStore.FoodStore;
import farmSystem.zerozeronbbang.domains.foodStore.repository.FoodStoreRepository;
import farmSystem.zerozeronbbang.domains.orderBoard.OrderBoard;
import farmSystem.zerozeronbbang.domains.orderBoard.dto.ReqWriteOrderBoardDto;
import farmSystem.zerozeronbbang.domains.orderBoard.dto.ResFindOrderBoardsDto;
import farmSystem.zerozeronbbang.domains.orderBoard.repository.OrderBoardRepository;
import farmSystem.zerozeronbbang.domains.user.User;
import farmSystem.zerozeronbbang.domains.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderBoardServiceImpl implements OrderBoardService{

    private final OrderBoardRepository orderBoardRepository;
    private final UserRepository userRepository;
    private final FoodStoreRepository foodStoreRepository;
    @Override
    public Page<ResFindOrderBoardsDto> findOrderBoards(Pageable pageable) {
        Page<OrderBoard> orderBoards = orderBoardRepository.findAllBy(pageable);
        return orderBoards.map(orderBoard ->
                ResFindOrderBoardsDto.builder()
                        .id(orderBoard.getId())
                        .numOfRecruit(orderBoard.getNumOfRecruit())
                        .endTime(orderBoard.getEndTime())
                        .address1(orderBoard.getAddress1())
                        .address2(orderBoard.getAddress2())
                        .address3(orderBoard.getAddress3())
                        .foodStoreName(orderBoard.getFoodStore().getName())
                        .deliveryTime(orderBoard.getFoodStore().getDeliveryTime())
                        .deliveryTip(orderBoard.getFoodStore().getDeliveryTip())
                        .minDeliveryPrice(orderBoard.getFoodStore().getMinDeliveryPrice())
                        .storePictureUrl(orderBoard.getFoodStore().getStorePictureUrl())
                        .completed(orderBoard.isCompleted())
                        .createdAt(orderBoard.getCreatedAt())
                        .build()
        );
    }

    @Override
    @Transactional // 결제 로직이 들어가야 함. 트랜잭션 적용 시켜서 결제 안되면 롤백해야 하기 때문.
    public ReqWriteOrderBoardDto writeOrderBoard(String email, ReqWriteOrderBoardDto reqWriteOrderBoardDto) {
        User user = userRepository.findUserByEmail(email).get();
        FoodStore foodStore = foodStoreRepository.findById(reqWriteOrderBoardDto.getFoodStoreId()).get();
        OrderBoard orderBoard = OrderBoard.builder()
                .address1(reqWriteOrderBoardDto.getAddress1())
                .address2(reqWriteOrderBoardDto.getAddress2())
                .address3(reqWriteOrderBoardDto.getAddress3())
                .numOfRecruit(reqWriteOrderBoardDto.getNumOfRecruit())
                .endTime(reqWriteOrderBoardDto.getEndTime())
                .storeRequest(reqWriteOrderBoardDto.getStoreRequest())
                .riderRequest(reqWriteOrderBoardDto.getRiderRequest())
                .title(reqWriteOrderBoardDto.getTitle())
                .completed(false)
                .build();
        orderBoard.setFoodStore(foodStore);
        orderBoard.setUser(user);

        //결제 로직

        orderBoardRepository.save(orderBoard);
        return reqWriteOrderBoardDto;
    }

}
