package farmSystem.zerozeronbbang.domains.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccessTokenDto {
    private String accessToken;
}