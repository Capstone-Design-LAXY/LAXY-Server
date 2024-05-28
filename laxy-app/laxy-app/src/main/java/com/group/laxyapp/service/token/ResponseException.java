package com.group.laxyapp.service.token;

public class ResponseException extends Exception {
    // 기본 생성자
    public ResponseException() {
        super();
    }

    // 예외 메시지를 받아들이는 생성자
    public ResponseException(String message) {
        super(message);
    }

    // 예외 메시지와 원인(cause)을 받아들이는 생성자
    public ResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    // 원인(cause)을 받아들이는 생성자
    public ResponseException(Throwable cause) {
        super(cause);
    }
}
