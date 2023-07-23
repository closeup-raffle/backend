package farmSystem.zerozeronbbang.response;

import farmSystem.zerozeronbbang.global.enums.ResCodeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomResponseEntity<T> {

    private int code;
    private String message;
    private T data;

    public static <T> CustomResponseEntity<T> success(T data) {
        return CustomResponseEntity.<T>builder()
                .code(ResCodeEnum.OK.getCode())
                .message(ResCodeEnum.OK.getMessage())
                .data(data)
                .build();
    }

    public static <T> CustomResponseEntity<T> success() {
        return CustomResponseEntity.<T>builder()
                .code(ResCodeEnum.OK.getCode())
                .message(ResCodeEnum.OK.getMessage())
                .build();
    }

    public static <T> CustomResponseEntity<T> fail() {
        return CustomResponseEntity.<T>builder()
                .code(ResCodeEnum.FAIL.getCode())
                .message(ResCodeEnum.FAIL.getMessage())
                .build();
    }

    public static <T> CustomResponseEntity<T> fail(ResCodeEnum resCodeEnum) {
        return CustomResponseEntity.<T>builder()
                .code(resCodeEnum.getCode())
                .message(resCodeEnum.getMessage())
                .build();
    }

    @Builder
    public CustomResponseEntity(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}