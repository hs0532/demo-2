package com.haoshuang.sso.demosso.common.validate.validateInterface;

import com.haoshuang.sso.demosso.common.validate.ValidateCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码存入session
 */
@Component
public class ValidateCodeReposityBySession implements ValidateCodeRepository{

    //验证码放入session时的前缀
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest servletWebRequest, ValidateCode code, ValidateCodeType validateCodeType) {
       // sessionStrategy.setAttribute(servletWebRequest,SESSION_KEY_PREFIX+validateCodeType.toString().toUpperCase(),code);
        sessionStrategy.setAttribute(servletWebRequest,getSessionKey(validateCodeType),code);
    }

    @Override
    public ValidateCode get(ServletWebRequest servletWebRequest, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(servletWebRequest,getSessionKey(validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest servletWebRequest, ValidateCodeType validateCodeType) {
        sessionStrategy.removeAttribute(servletWebRequest,getSessionKey(validateCodeType));
    }

    public String getSessionKey(ValidateCodeType validateCodeType){
       return SESSION_KEY_PREFIX+validateCodeType.toString().toUpperCase();
    }
}
