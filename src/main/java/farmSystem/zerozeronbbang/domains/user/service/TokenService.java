package farmSystem.zerozeronbbang.domains.user.service;

import org.springframework.security.core.Authentication;

public interface TokenService {
    String createAccessToken(final String payload);
    String createRefreshToken();
    String getPayload(final String token);
    boolean validateToken(final String token);

    Authentication getAuthentication(String accessToken);
}
