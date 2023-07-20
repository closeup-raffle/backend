package farmSystem.zerozeronbbang.domains.menu.service;

import farmSystem.zerozeronbbang.domains.menu.Menu;
import farmSystem.zerozeronbbang.domains.menu.dto.ReqFindMenuDto;
import farmSystem.zerozeronbbang.domains.menu.dto.ResFindMenuDto;
import farmSystem.zerozeronbbang.response.ResponseDto;

import java.util.List;

public interface MenuService {

    List<ResFindMenuDto> findMenus(ReqFindMenuDto reqFindMenuDto);
}
