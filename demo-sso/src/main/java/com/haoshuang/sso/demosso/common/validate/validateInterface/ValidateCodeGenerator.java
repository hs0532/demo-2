package com.haoshuang.sso.demosso.common.validate.validateInterface;

import com.haoshuang.sso.demosso.common.validate.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest servletWebRequest);
}
