package farmSystem.zerozeronbbang.global.security;

import farmSystem.zerozeronbbang.domains.user.service.Impl.UserAuthTokenServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final UserAuthTokenServiceImpl userAuthTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1. Request Header 에서 JWT Token 추출
        String authHeader = request.getHeader("Authorization");
        String bearerToken = "";
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) {
            bearerToken = authHeader.substring(7);
        }

        //2. validateToken 메서드로 토큰 유효성 검사
        if (bearerToken != null && userAuthTokenService.validateToken(bearerToken)) {
            if (!request.getRequestURI().equals("/v1/user/reissue")) {
                Authentication authentication = userAuthTokenService.getAuthentication(bearerToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
