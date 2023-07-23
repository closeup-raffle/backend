package farmSystem.zerozeronbbang.global.enums;

import lombok.Getter;

@Getter
public enum ResCodeEnum {

    OK(0, "Success"),
    FAIL(-1, "Fail"),



    USER_LOGIN_SUCCESS(1001,"Login Success"),
    USER_LOGIN_FAIL(1002,"Login Fail"),
    USER_SIGNUP_SUCCESS(1003,"User Signup Success"),
    DUPLICATED_EMAIL(1004,"User Email Duplicated"),
    UNCORRECTED_EMAIL(1005,"Uncorrect Email"),
    UNCORRECTED_PASSWORD(1006,"Uncorrect Password"),

    USER_SIGNUP_FAIL(1007,"User Signup Success"),

    USER_FIND_SUCCESS(1008,"Get User Success "),
    USER_NOT_FOUND(1009,"User Not Found"),

    USER_LOGOUT_SUCCESS(1010,"User Logout Success"),

    // 카테고리
    CATEGORY_LOAD_SUCCESS(2001,"Get Categories Success"),


    // 주문 공고
    ORDERBOARD_LOAD_SUCCESS(3001,"Get OrderBoards Success"),
    ORDERBOARD_WRITE_SUCCESS(3002,"Write OrderBoard Success"),


    //음식점
    FOODSTORE_LOAD_SUCCESS(4001,"Get FoodStore Success"),

    // 메뉴
    MENU_LOAD_SUCCESS(5001,"Get Menu Success"),

    ;

    private final int code;
    private final String message;

    ResCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResCodeEnum resolve(int code) {
        for (ResCodeEnum resCodeEnum : values()) {
            if (resCodeEnum.getCode() == code) {
                return resCodeEnum;
            }
        }
        return null;
    }
}
