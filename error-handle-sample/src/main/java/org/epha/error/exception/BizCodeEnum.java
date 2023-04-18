package org.epha.error.exception;

import org.springframework.http.HttpStatus;

/**
 * @author pangjiping
 */

public enum BizCodeEnum {

    INVALID_USERNAME_OR_PASSWORD(HttpStatus.BAD_REQUEST.value(), "InvalidParameter.UsernameOrPassword", "invalid username or password, please check it!"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Resource.NotFound","requested resource not found"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", "unauthorized action");

    private int code;
    private String message;
    private String describe;

    BizCodeEnum(int code, String message, String describe) {
        this.code = code;
        this.message = message;
        this.describe = describe;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDescribe() {
        return this.describe;
    }

}
