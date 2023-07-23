package farmSystem.zerozeronbbang.domains.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResOauthDto {
    private ResLoginDto resLoginDto;
    private ResOauthSignUp resOauthSignUp;
}
