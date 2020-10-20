package com.haoshuang.sso.demosso.common.validate.validateInterface;

import com.haoshuang.sso.demosso.common.validate.ValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsValidateCodeGenerator")
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ValidateCode generate(ServletWebRequest servletWebRequest) {
        return null;
    }
}
