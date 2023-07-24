package farmSystem.zerozeronbbang.domains.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReqLoginDto {
    private String email;
    private String password;
}
