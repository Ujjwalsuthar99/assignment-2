package com.example.assignment.constants;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private Boolean response = false;
    private T data;
    private Object err = null;

    public BaseResponse(T data) {
        this.response = true;
        this.data = data;
    }
    public BaseResponse(Boolean response, Object err) {
        this.response = response;
        this.data = null;
        this.err = err;
    }
}
