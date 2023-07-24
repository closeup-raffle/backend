package farmSystem.zerozeronbbang.domains.user.service;

import farmSystem.zerozeronbbang.domains.user.dto.response.ResOauthDto;

public interface OauthService {
    public String getKakaoAccessToken(String code);
    public ResOauthDto getKakaoUserInfo(String token);
}
