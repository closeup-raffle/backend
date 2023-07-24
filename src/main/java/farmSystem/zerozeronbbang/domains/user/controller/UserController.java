package farmSystem.zerozeronbbang.domains.user.controller;

import farmSystem.zerozeronbbang.domains.user.dto.request.ReqLoginDto;
import farmSystem.zerozeronbbang.domains.user.dto.request.ReqSignUpDto;
import farmSystem.zerozeronbbang.domains.user.dto.response.ResLoginDto;
import farmSystem.zerozeronbbang.domains.user.dto.response.ResSignUpDto;
import farmSystem.zerozeronbbang.domains.user.dto.response.ResOauthDto;
import farmSystem.zerozeronbbang.domains.user.service.Impl.OauthKaKaoServiceImpl;
import farmSystem.zerozeronbbang.domains.user.service.Impl.UserServiceImpl;
import farmSystem.zerozeronbbang.response.CustomResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "User", description = "유저 인증 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    private final OauthKaKaoServiceImpl oauthKaKaoService;

    // GET
//    @Operation(summary = "user 조회 TEST", description = "user login 컨트롤러")
//    @GetMapping(value = "/test")
//    public CustomResponseEntity<ResponseDto> test(@AuthenticationPrincipal User userAccount, String email) {
//        return ResponseUtil.SUCCESS(ResCodeEnum.USER_FIND_SUCCESS.getMessage(), userService.findUser(email));
//    }

    @ResponseBody
    @GetMapping("/login/kakao")
    public CustomResponseEntity<ResOauthDto> kakaoCalllback(@RequestParam String code) {
        String accessToken = oauthKaKaoService.getKakaoAccessToken(code);
        log.info(accessToken);
        return CustomResponseEntity.success(oauthKaKaoService.getKakaoUserInfo(accessToken));
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
