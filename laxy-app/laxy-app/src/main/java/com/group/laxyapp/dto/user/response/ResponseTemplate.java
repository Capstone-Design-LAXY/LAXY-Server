package com.group.laxyapp.dto.user.response;

public class ResponseTemplate<T> {
    private boolean success;
    private T data;
    private String message;

    public ResponseTemplate(T data) {
        this.success = true;
        this.data = data;
    }

    public ResponseTemplate(String message) {
        this.success = false;
        this.message = message;
    }

    // getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}