package com.haoshuang.sso.demosso.common.validate.validateInterface;

import org.springframework.web.context.request.ServletWebRequest;

/*
 验证码管理接口
 */
public interface ValidateCodeProcessor {


    // 生成验证码
    void create(ServletWebRequest servletWebRequest) throws Exception;

    // 校验验证码
    void validate(ServletWebRequest servletWebRequest);

    String creteBase64(ServletWebRequest servletWebRequest)throws Exception;
}
