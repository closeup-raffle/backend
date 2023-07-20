package farmSystem.zerozeronbbang.domains.user.service;

import farmSystem.zerozeronbbang.domains.user.dto.ReqLoginDto;
import farmSystem.zerozeronbbang.domains.user.dto.ReqSignUpDto;
import farmSystem.zerozeronbbang.domains.user.dto.ResLoginDto;
import farmSystem.zerozeronbbang.domains.user.dto.ResSignUpDto;

public interface UserService {
//    ResLoginDto login(String email, String password);

    ResLoginDto login(ReqLoginDto reqLoginDto);

    ResSignUpDto signUp(ReqSignUpDto reqSignUpDto);
    String findUser(String email);
}
