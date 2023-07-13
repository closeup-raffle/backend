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
    private Address address;
    private AccessAndRefreshTokenDto token;
}
