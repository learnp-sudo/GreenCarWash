package com.green.car.wash.company.admin.exceptionHandlers;

public class API_requestException extends RuntimeException{
	private String message;

    public API_requestException(String message, Throwable cause){
        super(message, cause);
        this.message=message;
       

    }

    public API_requestException(String message){
        super(message);
        this.message=message;
    }
}
