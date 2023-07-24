package farmSystem.zerozeronbbang.domains.user.dto.response;

import farmSystem.zerozeronbbang.domains.user.dto.AccessAndRefreshTokenDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResLoginDto {
    private String email;
    private Long id;
    private String name;
    private String phone;
    private String address1;
    private String address2;
    private String address3;
    private AccessAndRefreshTokenDto token;
}
