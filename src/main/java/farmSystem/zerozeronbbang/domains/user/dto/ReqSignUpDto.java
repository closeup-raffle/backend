package farmSystem.zerozeronbbang.domains.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReqSignUpDto {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
}
