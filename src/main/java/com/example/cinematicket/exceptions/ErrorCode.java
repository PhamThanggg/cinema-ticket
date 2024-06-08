package com.example.cinematicket.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
@AllArgsConstructor
public enum ErrorCode {

    //=================== USER BEGIN =====================
    PASSWORD_NOT_SAME(1001, "Passwords are not the same", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Email existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1004, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    //=================== USER END =======================

    //=================== CINEMA BEGIN =====================
    CINEMA_EXISTED(2001, "Cinema existed", HttpStatus.BAD_REQUEST),
    CINEMA_NOT_EXISTED(2001, "Cinema not existed", HttpStatus.BAD_REQUEST),
    //=================== CINEMA END =======================
    ;


    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
