package com.haoshuang.sso.demosso.common.validate;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code,int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    // 是否过期
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(getExpireTime());
    }

    public ValidateCode(String code,LocalDateTime expireTime){
        super();
        this.code = code;
        this.expireTime = expireTime;
    }

}
