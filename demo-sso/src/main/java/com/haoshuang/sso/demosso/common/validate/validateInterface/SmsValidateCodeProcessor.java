package com.haoshuang.sso.demosso.common.validate.validateInterface;

import com.haoshuang.sso.demosso.common.validate.ValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode>{

    @Override
    public void create(ServletWebRequest servletWebRequest) throws Exception {
        super.create(servletWebRequest);
    }

    @Override
    public void send(ServletWebRequest servletWebRequest, ValidateCode validateCode) throws Exception {

    }

    @Override
    public String sending(ServletWebRequest servletWebRequest, ValidateCode validateCode) throws Exception {
        return null;
    }
}
