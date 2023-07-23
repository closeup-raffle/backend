package farmSystem.zerozeronbbang.global.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Getter
@AllArgsConstructor
@RedisHash(value = "user", timeToLive = 600000)
public class UserRedis {
    @Id
    private Long memberId;
    private String email;
    private String name;
    private String phone;
    private String address1;
    private String address2;
    private String address3;
}
