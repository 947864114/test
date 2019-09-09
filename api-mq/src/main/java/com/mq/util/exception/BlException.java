package com.mq.util.exception;

public class BlException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private int status;
    private String msg;

    public BlException(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public BlException(int status) {
        this.msg = ExceptionStatus.getDesc(status);
        this.status = status;
    }

    public BlException(String messageKey) {
        super(messageKey);
    }

    public BlException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }

    public BlException(Throwable cause) {
        super(cause);
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
