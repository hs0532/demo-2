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


}
