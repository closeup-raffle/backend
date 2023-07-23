package farmSystem.zerozeronbbang.domains.user.controller;

import farmSystem.zerozeronbbang.domains.user.User;
import farmSystem.zerozeronbbang.domains.user.dto.ReqLoginDto;
import farmSystem.zerozeronbbang.domains.user.dto.ReqSignUpDto;
import farmSystem.zerozeronbbang.domains.user.dto.ResLoginDto;
import farmSystem.zerozeronbbang.domains.user.dto.ResSignUpDto;
import farmSystem.zerozeronbbang.domains.user.dto.ResOauthDto;
import farmSystem.zerozeronbbang.domains.user.service.Impl.OauthKaKaoServiceImpl;
import farmSystem.zerozeronbbang.domains.user.service.Impl.UserServiceImpl;
import farmSystem.zerozeronbbang.global.redis.UserRedis;
import farmSystem.zerozeronbbang.response.CustomResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "User", description = "유저 인증 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    private final OauthKaKaoServiceImpl oauthKaKaoService;

    // GET
    @Operation(summary = "user 조회 TEST", description = "user login 컨트롤러")
    @GetMapping(value = "/test")
    public void test(@AuthenticationPrincipal UserRedis userAccount, String email) {
        System.out.println("TEST" + userAccount.getEmail());
        // return ResponseUtil.SUCCESS(ResCodeEnum.USER_FIND_SUCCESS.getMessage(), userService.findUser(email));
    }

    @ResponseBody
    @GetMapping("/login/kakao")
    public ResOauthDto kakaoCalllback(@RequestParam String code) {
        String accesstoken = oauthKaKaoService.getKakaoAccessToken(code);
        return oauthKaKaoService.getKakaoUserInfo(accesstoken);
    }

    // POST
    @Operation(summary = "user 회원가입", description = "user login 컨트롤러")
    @PostMapping(value = "/sign-up")
    public CustomResponseEntity<ResSignUpDto> signUp(@RequestBody @Valid final ReqSignUpDto reqSignUpDto) {
        return CustomResponseEntity.success(userService.signUp(reqSignUpDto));
    }

    @Operation(summary = "user 로그인", description = "user login 컨트롤러")
    @PostMapping(value = "/login")
    public CustomResponseEntity<ResLoginDto> login(@RequestBody @Valid final ReqLoginDto reqLoginDto) {
        return CustomResponseEntity.success(userService.login(reqLoginDto));
//       ResLoginDto login = userService.login(reqLoginDto.getEmail(), reqLoginDto.getPassword());
//       if (login == null) {
//           return ResponseUtil.FAILURE(ResCodeEnum.USER_LOGIN_FAIL.getMessage(), null);
//       }
//
//       return ResponseUtil.SUCCESS(ResCodeEnum.USER_LOGIN_SUCCESS.getMessage(), login);
//    }
    }
}
