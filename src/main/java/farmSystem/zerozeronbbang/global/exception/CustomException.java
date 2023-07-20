package farmSystem.zerozeronbbang.global.exception;

import farmSystem.zerozeronbbang.global.enums.ResCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {

    private ResCodeEnum resCodeEnum;
    private String debug;

    public CustomException(ResCodeEnum resCodeEnum) {
        super(resCodeEnum.getMessage()); // 부모 클래스의 생성자 호출
        this.resCodeEnum = resCodeEnum;
        this.debug = resCodeEnum.getMessage();
    }
}