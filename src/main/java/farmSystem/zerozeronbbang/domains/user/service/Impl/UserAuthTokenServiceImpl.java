package farmSystem.zerozeronbbang.domains.user.service.Impl;

import farmSystem.zerozeronbbang.domains.user.User;
import farmSystem.zerozeronbbang.domains.user.service.TokenService;
import farmSystem.zerozeronbbang.global.redis.UserRedis;
import farmSystem.zerozeronbbang.global.redis.UserRedisRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAuthTokenServiceImpl implements TokenService {
    @Value("${token.jwt.secret}")
    private String jwtSecret;

    @Value("${token.jwt.ttl}")
    private int jwtExpirationTime;

    private final UserRedisRepository userRedisRepository;

    @Override
    public String createAccessToken(User user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtExpirationTime);

        return Jwts.builder()
                .claim("id", user.getId())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    @Override
    public String createRefreshToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getPayload(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
//          log.info("Invalid JWT Token", e);
            System.out.println("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
//          log.info("Expired JWT Token", e);
            System.out.println("Expired JWT Token");
        } catch (UnsupportedJwtException e) {
//          log.info("Unsupported JWT Token", e);
            System.out.println("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
//          log.info("JWT claims string is empty.", e);
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    @Override
    //JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(accessToken)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("id").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // redis에서 유저 정보 가져오기
        Optional<UserRedis> userRedis = userRedisRepository.findById(claims.get("id").toString());
        return new UsernamePasswordAuthenticationToken(userRedis.get(), accessToken, authorities);
    }
}
