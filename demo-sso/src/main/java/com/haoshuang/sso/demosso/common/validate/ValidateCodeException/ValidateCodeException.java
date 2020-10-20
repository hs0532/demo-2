package com.haoshuang.sso.demosso.common.validate.ValidateCodeException;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {
    private String msg;
    private Integer code;

    public ValidateCodeException(String msg,Integer code){
        super(msg);
        this.msg = msg;
        this.code = code;
    }
    public ValidateCodeException(String msg){
        super(msg);
        this.msg = msg;
        this.code=1;
    }


}
