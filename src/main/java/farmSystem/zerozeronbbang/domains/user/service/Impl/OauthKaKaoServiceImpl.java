package farmSystem.zerozeronbbang.domains.user.service.Impl;

import farmSystem.zerozeronbbang.domains.user.dto.ReqLoginDto;
import farmSystem.zerozeronbbang.domains.user.dto.ReqSignUpDto;
import farmSystem.zerozeronbbang.domains.user.dto.ResOauthDto;
import farmSystem.zerozeronbbang.domains.user.dto.ResOauthSignUp;
import farmSystem.zerozeronbbang.domains.user.service.OauthService;
import farmSystem.zerozeronbbang.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OauthKaKaoServiceImpl implements OauthService {

    private final UserServiceImpl userService;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    public String getKakaoAccessToken(String code) {
        String reqURL = "https://kauth.kakao.com";
        /**
         * code값을 이용하여 token정보 가져오기
         */
        // webClient 설정
        WebClient kakaoWebClient =
                WebClient.builder()
                        .baseUrl(reqURL)
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();

        // token api 호출
        Map<String, Object> tokenResponse =
                kakaoWebClient
                        .post()
                        .uri(uriBuilder -> uriBuilder
                                .path("/oauth/token")
                                .queryParam("grant_type", "authorization_code")
                                .queryParam("client_id", clientId)
                                .queryParam("redirect_uri", redirectUri)
                                .queryParam("code", code)
                                .build())
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();

        String accessToken = (String) tokenResponse.get("access_token");
        return accessToken;
    }

    public ResOauthDto getKakaoUserInfo(String token) {
        String reqURL = "https://kapi.kakao.com";

        /**
         * accessToken으로 로그인 사용자가 동의한 정보 확인하기
         */
        // webClient 설정
        WebClient kakaoApiWebClient =
                WebClient.builder()
                        .baseUrl(reqURL)
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();

        // info api 설정
        Map<String, Object> infoResponse =
                kakaoApiWebClient
                        .post()
                        .uri(uriBuilder -> uriBuilder
                                .path("/v2/user/me")
                                .build())
                        .header("Authorization", "Bearer " + token)
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();
        Map<String, Object> kakaoAccountMap = (Map<String, Object>) infoResponse.get("kakao_account");
        Map<String, String> profileMap = (Map<String, String>) kakaoAccountMap.get("profile");

        // 로그인 시도
        ResOauthDto resOauthDto = new ResOauthDto(null, null);
        String id = String.valueOf((Long) infoResponse.get("id"));
        try{
            resOauthDto.setResLoginDto(this.userService.login(new ReqLoginDto(id, null)));
        } catch (CustomException e) {
            String name = "";

            // 닉네임 정보 담기
            if (StringUtils.hasText(profileMap.get("nickname"))) {
                name = profileMap.get("nickname");
            }

            // 회원 정보 만들기
            resOauthDto.setResOauthSignUp(new ResOauthSignUp(id, name));
        }

        return resOauthDto;
    }
}
