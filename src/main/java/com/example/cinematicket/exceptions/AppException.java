package com.example.cinematicket.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppException extends RuntimeException {
    private String customMessage;
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public AppException(ErrorCode errorCode, String customMessage) {
        super(errorCode.getMessage() + (customMessage != null ? customMessage : ""));
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getCustomMessage() {
        String mes = null;
        if(customMessage!=null){
            mes = errorCode.getMessage() + customMessage;
        }
        return mes;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
