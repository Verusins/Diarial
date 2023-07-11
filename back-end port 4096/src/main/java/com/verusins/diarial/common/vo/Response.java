package com.verusins.diarial.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public static<T> Response<T> success(){
        return new Response<>(20000, "success", null);
    }

    public static<T> Response<T> success(T data){
        return new Response<>(20000, "success", data);
    }

    public static<T> Response<T> success(String message){
        return new Response<>(20000, message, null);
    }

    public static<T> Response<T> success(String message, T data){
        return new Response<>(20000, message, data);
    }

    public static<T> Response<T> fail(){
        return new Response<>(20001, "bad request", null);
    }

    public static<T> Response<T> fail(String message){
        return new Response<>(20001, message, null);
    }

    public static<T> Response<T> fail(int code, String message){
        return new Response<>(code, message, null);
    }
}
