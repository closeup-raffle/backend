package farmSystem.zerozeronbbang.domains.user.service.Impl;

import farmSystem.zerozeronbbang.domains.user.User;
import farmSystem.zerozeronbbang.domains.user.dto.*;
import farmSystem.zerozeronbbang.domains.user.repository.UserRepository;
import farmSystem.zerozeronbbang.domains.user.service.UserService;
import farmSystem.zerozeronbbang.global.enums.ResCodeEnum;
import farmSystem.zerozeronbbang.global.exception.CustomException;
import farmSystem.zerozeronbbang.global.redis.RefreshToken;
import farmSystem.zerozeronbbang.global.redis.RefreshTokenRedisRepository;
import farmSystem.zerozeronbbang.global.redis.UserRedis;
import farmSystem.zerozeronbbang.global.redis.UserRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final UserRedisRepository userRedisRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthTokenServiceImpl userAuthTokenService;

//    /**
//     * login
//     * @param email
//     * @param password
//     * @return
//     */
    @Override
    @Transactional
    public ResLoginDto login(ReqLoginDto reqLoginDto) {
        Optional<User> optionalUser = userRepository.findUserByEmail(reqLoginDto.getEmail());

        if (optionalUser.isEmpty()) {
            throw new CustomException(ResCodeEnum.UNCORRECTED_EMAIL);
        }

        User user = optionalUser.get();
        // kakao 로그인의 경우 비밀번호 없음
        if (user.getPassword() == null && reqLoginDto.getPassword() == null) {}
        else if (!passwordEncoder.matches(reqLoginDto.getPassword(), user.getPassword())) {
            throw new CustomException(ResCodeEnum.UNCORRECTED_PASSWORD);
        }

        RefreshToken refreshToken = new RefreshToken(userAuthTokenService.createRefreshToken(), user.getId());
        AccessAndRefreshTokenDto token = new AccessAndRefreshTokenDto(userAuthTokenService.createAccessToken(user), refreshToken.getRefreshToken());
        // Redis RefreshToken 저장
        refreshTokenRedisRepository.save(refreshToken);

        // Redis user 저장
        UserRedis userRedis = new UserRedis(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.getAddress1(), user.getAddress2(), user.getAddress3());
        userRedisRepository.save(userRedis);

        ResLoginDto resLoginDto = ResLoginDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .address1(user.getAddress1())
                .address2(user.getAddress2())
                .address3(user.getAddress3())
                .token(token)
                .build();

        return resLoginDto;
    }

    /**
     * sign up
     *
     * @param reqSignUpDto
     * @return
     */
    @Override
    @Transactional
    public ResSignUpDto signUp(ReqSignUpDto reqSignUpDto) {

        //아이디 중복 검사
        validateDuplicatedUserEmail(reqSignUpDto.getEmail());

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

        ResSignUpDto resSignUpDto = ResSignUpDto.builder()
                .id(user.getId())
                .email(reqSignUpDto.getEmail())
                .name(reqSignUpDto.getName())
                .build();
        return resSignUpDto;
    }

    private void validateDuplicatedUserEmail(String userEmail) {
        Boolean existsByNickName = userRepository.existsByEmail(userEmail);
        if (existsByNickName) {
            throw new CustomException(ResCodeEnum.DUPLICATED_EMAIL);
        }
    }

    @Override
    public String findUser(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        return optionalUser.get().getName();
    }


}
