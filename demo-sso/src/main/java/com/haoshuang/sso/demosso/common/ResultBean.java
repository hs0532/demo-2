package com.haoshuang.sso.demosso.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBean<C> implements Serializable {
    private int cade = 1;
    private String msg;
    private C data;

    public ResultBean() {
    }

    private ResultBean(int code, String msg){
        this.cade = code;
        this.msg = msg;
    }

    public ResultBean(int cade, String msg, C data) {
        this.cade = cade;
        this.msg = msg;
        this.data = data;
    }

    public ResultBean(int cade) {
        this.cade = cade;
    }
    public static <T> ResultBean<T> createBySuccess(){
        return new ResultBean<T>(ResultEnm.SUCCESS.getCode());
    }

    public static <T> ResultBean<T> createBySuccess(String msg){
        return new ResultBean<T>(ResultEnm.SUCCESS.getCode(),msg);
    }

//    public static <T> ResultBean<T> createBySuccess(T data){
//        return new ResultBean<T>(ResponseCode.SUCCESS.getCode(),data);
//    }
//
//    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
//        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
//    }
//
//    public static <T> ServerResponse<T> createByError(String msg,T data){
//        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),msg,data);
//    }
//
//
//    public static <T> ServerResponse<T> createByError(){
//        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
//    }


    public static <T> ResultBean<T> createByError(String errorMessage){
        return new ResultBean<T>(ResultEnm.ERROR.getCode(),errorMessage);
    }

    public static <T> ResultBean<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ResultBean<T>(errorCode,errorMessage);
    }

}
