package com.wxs.shiro.event;

public class ExceptionEvent {
    private String message;

    public ExceptionEvent() {
    }

    public ExceptionEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
