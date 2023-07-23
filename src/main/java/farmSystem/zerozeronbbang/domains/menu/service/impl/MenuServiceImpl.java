package farmSystem.zerozeronbbang.domains.menu.service.impl;

import farmSystem.zerozeronbbang.domains.menu.Menu;
import farmSystem.zerozeronbbang.domains.menu.dto.ResFindMenuDto;
import farmSystem.zerozeronbbang.domains.menu.repository.MenuRepository;
import farmSystem.zerozeronbbang.domains.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResFindMenuDto> findMenus(Long foodStoreId) {
        List<Menu> menus = menuRepository.findByFoodStoreId(foodStoreId);

        return menus.stream()
                .map(menu -> ResFindMenuDto.builder()
                        .menuPhotoUrl(menu.getMenuPhotoUrl())
                        .price(menu.getPrice())
                        .title(menu.getTitle())
                        .build())
                .collect(Collectors.toList());

    }
}
