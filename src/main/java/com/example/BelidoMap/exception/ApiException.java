package com.example.BelidoMap.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException{
    private HttpStatus httpStatus;

    public ApiException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public static ApiException userNotFound(String mensaje){
        return  new ApiException(mensaje,HttpStatus.NOT_FOUND);
    }

    public static ApiException valorYaEnUso(String mensaje){
        return  new ApiException(mensaje,HttpStatus.CONFLICT);
    }




}
