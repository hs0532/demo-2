package com.haoshuang.sso.demosso.config.ValidateCode.validateInterface;

import org.springframework.web.context.request.ServletWebRequest;

/*
 验证码管理接口
 */
public interface ValidateCodeProcessor {

    //验证码放入session时的前缀
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    // 生成验证码
    void create(ServletWebRequest servletWebRequest) throws Exception;

    // 校验验证码
    void validate(ServletWebRequest servletWebRequest);
}
