package com.haoshuang.sso.demosso.config.ValidateCode.validateInterface;

import com.haoshuang.sso.demosso.config.ValidateCode.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码缓存操作方法
 */
public interface ValidateCodeRepository {
    void save(ServletWebRequest servletWebRequest, ValidateCode code, ValidateCodeType validateCodeType);

    ValidateCode get(ServletWebRequest servletWebRequest,ValidateCodeType validateCodeType);

    void remove(ServletWebRequest servletWebRequest,ValidateCodeType validateCodeType);

}
