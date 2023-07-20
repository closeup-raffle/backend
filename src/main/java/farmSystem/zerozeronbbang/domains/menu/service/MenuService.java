package farmSystem.zerozeronbbang.domains.menu.service;

import farmSystem.zerozeronbbang.domains.menu.dto.ResFindMenuDto;

import java.util.List;

public interface MenuService {

    List<ResFindMenuDto> findMenus(Long foodStoreId);
}
