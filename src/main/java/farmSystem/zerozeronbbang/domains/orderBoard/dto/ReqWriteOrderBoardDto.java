package farmSystem.zerozeronbbang.domains.orderBoard.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqWriteOrderBoardDto {
    // 수령 주소
    private String address1;
    private String address2;
    private String address3;
    private int numOfRecruit;
    private Long foodStoreId;
    private String endTime;
    private String storeRequest;
    private String riderRequest;
    private String title;


}
