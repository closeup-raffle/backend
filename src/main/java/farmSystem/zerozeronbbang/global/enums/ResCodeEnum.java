package farmSystem.zerozeronbbang.global.enums;

import lombok.Getter;

@Getter
public enum ResCodeEnum {
    USER_LOGIN_SUCCESS("Login Success"),
    USER_LOGIN_FAIL("Login Fail"),
    USER_SIGNUP_SUCCESS("User Signup Success"),
    USER_FIND_SUCCESS("Get User Success "),
    ;

    private String message;
    ResCodeEnum(String s) {
        this.message = s;
    }
}
