package farmSystem.zerozeronbbang.domains.user.service.Impl;

import farmSystem.zerozeronbbang.domains.user.User;
import farmSystem.zerozeronbbang.domains.user.dto.AccessAndRefreshTokenDto;
import farmSystem.zerozeronbbang.domains.user.dto.AccessTokenDto;
import farmSystem.zerozeronbbang.domains.user.dto.ReqSignUpDto;
import farmSystem.zerozeronbbang.domains.user.dto.ResLoginDto;
import farmSystem.zerozeronbbang.domains.user.repository.UserRepository;
import farmSystem.zerozeronbbang.domains.user.service.UserService;
import farmSystem.zerozeronbbang.global.redis.RefreshToken;
import farmSystem.zerozeronbbang.global.redis.RefreshTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthTokenServiceImpl userAuthTokenService;

    /**
     * login
     * @param email
     * @param password
     * @return
     */
    @Override
    public ResLoginDto login(String email, String password) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        RefreshToken refreshToken = new RefreshToken(userAuthTokenService.createRefreshToken(), user.getId());
        AccessAndRefreshTokenDto token = new AccessAndRefreshTokenDto(userAuthTokenService.createAccessToken(user), refreshToken.getRefreshToken());
        // Redis RefreshToken 저장
        refreshTokenRedisRepository.save(refreshToken);

        return ResLoginDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .address1(user.getAddress1())
                .address2(user.getAddress2())
                .address3(user.getAddress3())
                .token(token)
                .build();
    }

    /**
     * sign up
     * @param reqSignUpDto
     * @return
     */
    @Override
    public ReqSignUpDto signUp(ReqSignUpDto reqSignUpDto) {
        String password = passwordEncoder.encode(reqSignUpDto.getPassword());
        User user = User.builder()
                .email(reqSignUpDto.getEmail())
                .password(password)
                .name(reqSignUpDto.getName())
                .phone(reqSignUpDto.getPhone())
                .address1(reqSignUpDto.getAddress1())
                .address2(reqSignUpDto.getAddress2())
                .address3(reqSignUpDto.getAddress3())
                .build();
        userRepository.save(user);
        return reqSignUpDto;
    }

    @Override
    public String findUser(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        return optionalUser.get().getName();
    }


}
