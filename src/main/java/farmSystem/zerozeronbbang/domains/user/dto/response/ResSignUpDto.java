package farmSystem.zerozeronbbang.domains.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ResSignUpDto {

    private Long id;
    private String email;
    private String name;

}
