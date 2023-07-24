package farmSystem.zerozeronbbang.domains.user.service;

import farmSystem.zerozeronbbang.domains.user.dto.request.ReqLoginDto;
import farmSystem.zerozeronbbang.domains.user.dto.request.ReqSignUpDto;
import farmSystem.zerozeronbbang.domains.user.dto.response.ResLoginDto;
import farmSystem.zerozeronbbang.domains.user.dto.response.ResSignUpDto;

public interface UserService {
//    ResLoginDto login(String email, String password);

    ResLoginDto login(ReqLoginDto reqLoginDto);

    ResSignUpDto signUp(ReqSignUpDto reqSignUpDto);
    String findUser(String email);
}
