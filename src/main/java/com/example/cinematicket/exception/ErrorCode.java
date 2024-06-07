package com.example.cinematicket.exception;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
@AllArgsConstructor
public enum ErrorCode {
    PASSWORD_NOT_SAME(1001, "Passwords are not the same", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Email existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1004, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
