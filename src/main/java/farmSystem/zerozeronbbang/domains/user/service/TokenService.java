package farmSystem.zerozeronbbang.domains.user.service;

import farmSystem.zerozeronbbang.domains.user.User;
import org.springframework.security.core.Authentication;

public interface TokenService {
    String createAccessToken(User user);
    String createRefreshToken();
    String getPayload(final String token);
    boolean validateToken(final String token);

    Authentication getAuthentication(String accessToken);
}
