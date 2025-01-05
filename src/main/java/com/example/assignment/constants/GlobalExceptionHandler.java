package com.example.assignment.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({CustomException.class})
    private ResponseEntity<Object> handleCustomException(CustomException e) {
        log.info("Custom Error {}", e.getMessage());
        return new ResponseEntity<>(new BaseResponse<>(false, e.getMsg()), e.getHttpStatus());
    }

    @ExceptionHandler({Exception.class})
    private ResponseEntity<Object> handleException(Exception e) {
        log.info("Exception Error {}", e.getMessage());
        return new ResponseEntity<>(new BaseResponse<>(false, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
