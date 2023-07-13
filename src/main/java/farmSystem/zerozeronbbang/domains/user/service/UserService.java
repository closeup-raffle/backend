package farmSystem.zerozeronbbang.domains.user.service;

import farmSystem.zerozeronbbang.domains.user.User;
import farmSystem.zerozeronbbang.domains.user.dto.ReqSignUpDto;
import farmSystem.zerozeronbbang.domains.user.dto.ResLoginDto;

import java.util.Optional;

public interface UserService {
    ResLoginDto login(String email, String password);
    ReqSignUpDto signUp(ReqSignUpDto reqSignUpDto);
    String findUser(String email);
}
