package farmSystem.zerozeronbbang.domains.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ResFindMenuDto {

    private String menuPhotoUrl;
    private int price;
    private String title;
}
