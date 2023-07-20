package farmSystem.zerozeronbbang.domains.foodStore.repository;

import farmSystem.zerozeronbbang.domains.foodStore.FoodStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodStoreRepository extends JpaRepository<FoodStore, Long> {

    List<FoodStore> findByCategoryId(Long categoryId);

}
