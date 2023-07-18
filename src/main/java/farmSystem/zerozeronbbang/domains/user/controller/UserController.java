package farmSystem.zerozeronbbang.domains.user.controller;

import farmSystem.zerozeronbbang.domains.user.User;
import farmSystem.zerozeronbbang.domains.user.dto.ReqLoginDto;
import farmSystem.zerozeronbbang.domains.user.dto.ReqSignUpDto;
import farmSystem.zerozeronbbang.domains.user.dto.ResLoginDto;
import farmSystem.zerozeronbbang.domains.user.service.Impl.UserServiceImpl;
import farmSystem.zerozeronbbang.global.enums.ResCodeEnum;
import farmSystem.zerozeronbbang.response.ResponseDto;
import farmSystem.zerozeronbbang.response.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "유저 인증 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    // GET
    @Operation(summary = "user 조회 TEST", description = "user login 컨트롤러")
    @GetMapping(value = "/test")
    public ResponseDto<?> test(@AuthenticationPrincipal User userAccount, String email) {
        System.out.println("TEST" + userAccount.getId() + userAccount.getName() + userAccount.getAddress());
        return ResponseUtil.SUCCESS(ResCodeEnum.USER_FIND_SUCCESS.getMessage(), userService.findUser(email));
    }

    // POST
    @Operation(summary = "user 회원가입", description = "user login 컨트롤러")
    @PostMapping(value = "/sign-up")
    public ResponseDto<?> signUp(@RequestBody ReqSignUpDto reqSignUpDto) {
        return ResponseUtil.SUCCESS(ResCodeEnum.USER_SIGNUP_SUCCESS.getMessage(), userService.signUp(reqSignUpDto));
    }

    @Operation(summary = "user 로그인", description = "user login 컨트롤러")
    @PostMapping(value = "/login")
    public ResponseDto<ResLoginDto> login(@RequestBody ReqLoginDto reqLoginDto) {
       ResLoginDto login = userService.login(reqLoginDto.getEmail(), reqLoginDto.getPassword());
       if (login == null) {
           return ResponseUtil.FAILURE(ResCodeEnum.USER_LOGIN_FAIL.getMessage(), null);
       }

       return ResponseUtil.SUCCESS(ResCodeEnum.USER_LOGIN_SUCCESS.getMessage(), login);
    }

}
