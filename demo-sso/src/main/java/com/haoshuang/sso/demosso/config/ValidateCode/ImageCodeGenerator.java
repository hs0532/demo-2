package com.haoshuang.sso.demosso.config.ValidateCode;

import com.haoshuang.sso.demosso.config.ValidateCode.validateInterface.ValidateCodeProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.context.request.ServletWebRequest;


@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
public class ImageCodeGenerator implements ValidateCodeProcessor {

    @Override
    public void create(ServletWebRequest servletWebRequest) throws Exception {

    }

    @Override
    public void validate(ServletWebRequest servletWebRequest) {

    }
}
