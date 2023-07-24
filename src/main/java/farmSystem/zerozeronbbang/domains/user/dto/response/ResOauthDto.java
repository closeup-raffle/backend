package farmSystem.zerozeronbbang.domains.user.dto.response;

import farmSystem.zerozeronbbang.domains.user.dto.request.ReqSignUpDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResOauthDto {
    private ResLoginDto resLoginDto;
    private ReqSignUpDto reqSignUpDto;
}
