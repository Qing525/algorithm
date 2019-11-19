package com.wld.email.core.exception;

/**
 * 邮件发送异常
 *
 * @author zhangcf
 */
public class MailException extends RuntimeException {

    private String code;

    private String errorMessage;

    public MailException(String code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
