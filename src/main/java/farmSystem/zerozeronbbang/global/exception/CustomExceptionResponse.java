package farmSystem.zerozeronbbang.global.exception;

import lombok.*;

@Getter
@Setter
public class CustomExceptionResponse extends RuntimeException {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class entryPointResponse<T>{
        private Integer code;
        private String message;
        private T data;

        public static entryPointResponse response(CustomException customException){
            return entryPointResponse
                    .builder()
                    .code(customException.getResCodeEnum().getCode())
                    .message(customException.getResCodeEnum().getMessage())
                    .data(null)
                    .build();
        }
    }
}