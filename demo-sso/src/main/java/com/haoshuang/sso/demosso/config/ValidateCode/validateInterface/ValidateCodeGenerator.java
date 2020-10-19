package com.haoshuang.sso.demosso.config.ValidateCode.validateInterface;

import com.haoshuang.sso.demosso.config.ValidateCode.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest servletWebRequest);
}
