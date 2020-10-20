package com.haoshuang.sso.demosso.common.validate;

import com.haoshuang.sso.demosso.common.validate.ValidateCodeException.ValidateCodeException;
import com.haoshuang.sso.demosso.common.validate.validateInterface.ValidateCodeProcessor;
import com.haoshuang.sso.demosso.common.validate.validateInterface.ValidateCodeType;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Log4j
@Component
public class ValidateCodeProcessorHolder {

   @Autowired
   private Map<String, ValidateCodeProcessor> validateCodeProcessors;

   public  ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
       return findValidateCodeProcessor(type.toString().toLowerCase());
   }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
       ValidateCodeProcessor processor = validateCodeProcessors.get(name);
       if (processor == null) {
           log.info("验证码处理器" + name + "不存在");
            throw new ValidateCodeException("验证码处理器" + name + "不存在");

       }
       return processor;
    }
}
