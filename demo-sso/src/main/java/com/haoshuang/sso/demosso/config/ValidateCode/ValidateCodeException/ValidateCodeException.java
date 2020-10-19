package com.haoshuang.sso.demosso.config.ValidateCode.ValidateCodeException;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg){
        super(msg);
    }
}
