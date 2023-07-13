package farmSystem.zerozeronbbang.global.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Getter
@AllArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = 600000)
public class RefreshToken {
    @Id
    private String refreshToken;
    private Long memberId;
}
