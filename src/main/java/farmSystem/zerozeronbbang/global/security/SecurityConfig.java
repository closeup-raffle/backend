package farmSystem.zerozeronbbang.global.security;

import farmSystem.zerozeronbbang.domains.user.service.Impl.UserAuthTokenServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
//    private final UserServiceImpl userService;

    private final UserAuthTokenServiceImpl userAuthTokenService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                // Spring Security 세션 정책 : 세션을 생성 및 사용하지 않음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/login", "/user/sign-up").permitAll()
                .anyRequest().authenticated()
                .and()
                // JWT 인증 필터 적용
                .addFilterAfter(new TokenFilter(userAuthTokenService), BasicAuthenticationFilter.class)
                ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new ZeroPasswordEncoder();
    }
}
