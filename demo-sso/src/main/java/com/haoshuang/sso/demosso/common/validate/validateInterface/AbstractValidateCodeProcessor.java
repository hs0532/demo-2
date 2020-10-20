package com.haoshuang.sso.demosso.common.validate.validateInterface;

import com.haoshuang.sso.demosso.common.validate.ValidateCode;
import com.haoshuang.sso.demosso.common.validate.ValidateCodeException.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * 验证码处理器
 * @param <O>
 */
public abstract class AbstractValidateCodeProcessor<O extends ValidateCode> implements ValidateCodeProcessor{

    @Autowired
    private Map<String, ValidateCodeGenerator> validateGenerators;

    @Autowired
    ValidateCodeRepository validateCodeRepository;

    @Override
    public void create(ServletWebRequest servletWebRequest) throws Exception {
        O validateCode = generator(servletWebRequest);
        validateCodeRepository.save(servletWebRequest,new ValidateCode(validateCode.getCode(),validateCode.getExpireTime()),getCodeType(servletWebRequest));
        send(servletWebRequest,validateCode);
    }

    /**
     * 获取验证码生成器并生成验证码；
     * @param servletWebRequest
     * @return
     */
    public O generator(ServletWebRequest servletWebRequest){
        String type = getCodeType(servletWebRequest).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateGeneratetor = validateGenerators.get(generatorName);
        if (validateGeneratetor==null){
            throw new ValidateCodeException("验证码生成器"+generatorName+"不存在",1);
        }
        return (O) validateGeneratetor.generate(servletWebRequest);
    }

    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType codeType = getCodeType(request);
        // 拿到之前存储的imageCode信息
        ValidateCode validateCode = validateCodeRepository.get(request,codeType);
        String codeInRequest = null;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("验证码失效");
        }
        if (StringUtils.isBlank(codeInRequest)) {
                throw new ValidateCodeException("验证码的值不能为空");
            }
            if (codeInRequest == null) {
                throw new ValidateCodeException("验证码不存在");
            }
            if (validateCode.isExpired()) {
                //移除验证码
                validateCodeRepository.remove(request,codeType);
                throw new ValidateCodeException("验证码已过期");
            }
            if (!StringUtils.equals(validateCode.getCode(), codeInRequest)) {
                throw new ValidateCodeException("验证码不匹配");
            }
        //移除验证码
        validateCodeRepository.remove(request,codeType);

    }

    @Override
    public String creteBase64(ServletWebRequest servletWebRequest) throws Exception {
        O validateCode = generator(servletWebRequest);
        validateCodeRepository.save(servletWebRequest,new ValidateCode(validateCode.getCode(),validateCode.getExpireTime()),getCodeType(servletWebRequest));
        return sending(servletWebRequest,validateCode);
    }

    public abstract void send(ServletWebRequest servletWebRequest,O validateCode) throws Exception;
    public abstract String sending(ServletWebRequest servletWebRequest,O validateCode) throws Exception;

    public ValidateCodeType getCodeType(ServletWebRequest servletWebRequest){
        String type = StringUtils.substringBefore(getClass().getSimpleName(),"ValidateCodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }
}
