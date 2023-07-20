package farmSystem.zerozeronbbang.domains.user.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class ReqSignUpDto {

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    @NotBlank(message = "휴대폰 번호는 필수입니다.")
    private String phone;
    @NotBlank(message = "우편번호는 필수입니다.")
    private String address1;
    @NotBlank(message = "주소는 필수입니다.")
    private String address2;
    @NotBlank(message = "상세주소는 필수입니다.")
    private String address3;
}
