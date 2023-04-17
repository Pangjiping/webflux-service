package org.epha.web.exception;

/**
 * @author pangjiping
 */
public class BizException extends RuntimeException {
    private int code;

    private String message;

    private String describe;

//    public BizException(String message) {
//        super(message);
//        this.message = message;
//    }

    /**
     * 只能通过BizCodeEnum枚举类来构造自定义异常
     */
    public BizException(BizCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.describe=codeEnum.getDescribe();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDescribe() {
        return describe;
    }
}
