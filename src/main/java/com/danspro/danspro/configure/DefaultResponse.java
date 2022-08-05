package com.danspro.danspro.configure;

import org.springframework.http.HttpStatus;

public class DefaultResponse<T> {
    private int status;
    private String message;
    private T data;

    public void setStatus (int sttaus) {
        this.status = status;
    }
    public int getStatus(){
        return status;
    }
    public void setMessage (String messsage){
        this.message= message;
    }
    public String getMessage (){
       return message;
    }
    public void setData(T data) {
        this.data = data;
    }
    public T getData() {
        return data;
    }

    public DefaultResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> DefaultResponse<T> ok(T data){
        return new DefaultResponse(HttpStatus.OK.value(), "SUCCESS", data);
    }

    public static <T> DefaultResponse<T> ok(T data, String message){
        return new DefaultResponse(HttpStatus.OK.value(), message, data);
    }

    public static <T> DefaultResponse<T> error(String message){
        return new DefaultResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }
    
}
