package farmSystem.zerozeronbbang.global.enums;

import lombok.Getter;

@Getter
public enum ResCodeEnum {
    USER_LOGIN_SUCCESS("Login Success"),
    USER_LOGIN_FAIL("Login Fail"),
    USER_SIGNUP_SUCCESS("User Signup Success"),
    USER_FIND_SUCCESS("Get User Success "),

    // 카테고리
    CATEGORY_LOAD_SUCCESS("Get Categories Success"),


    // 주문 공고
    ORDERBOARD_LOAD_SUCCESS("Get OrderBoards Success"),
    ORDERBOARD_WRITE_SUCCESS("Write OrderBoard Success"),


    //음식점
    FOODSTORE_LOAD_SUCCESS("Get FoodStore Success"),

    // 메뉴
    MENU_LOAD_SUCCESS("Get Menu Success"),

    ;

    private String message;
    ResCodeEnum(String s) {
        this.message = s;
    }
}
