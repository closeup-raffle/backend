package farmSystem.zerozeronbbang.domains.menu.repository;

import farmSystem.zerozeronbbang.domains.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByFoodStoreId(Long foodStoreId);
}
