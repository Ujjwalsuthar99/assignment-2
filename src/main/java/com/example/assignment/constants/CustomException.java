package com.example.assignment.constants;


import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final String msg;
    private final int code;
    private final HttpStatus httpStatus;

    public CustomException(String msg, int code, HttpStatus httpStatus) {
        super(msg);
        this.msg = msg;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
        this.code = 99999;
        this.httpStatus = HttpStatus.FOUND;
    }

    public String getMsg() {
        return msg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
