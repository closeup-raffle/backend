package farmSystem.zerozeronbbang.global.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ZeroPasswordEncoder implements PasswordEncoder {

    private static final int STRENGTH = 10;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ZeroPasswordEncoder() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(STRENGTH);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
