package farmSystem.zerozeronbbang.domains.user.dto;

import farmSystem.zerozeronbbang.domains.user.Address;
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
