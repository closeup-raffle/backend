package farmSystem.zerozeronbbang.domains.orderBoard.repository;

import farmSystem.zerozeronbbang.domains.orderBoard.OrderBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBoardRepository extends JpaRepository<OrderBoard, Long> {

    Page<OrderBoard> findAllBy(Pageable pageable);

}
