package com.haoshuang.sso.demosso.config.ValidateCode;

import com.haoshuang.sso.demosso.config.ValidateCode.ValidateCodeException.ValidateCodeException;
import com.haoshuang.sso.demosso.config.ValidateCode.validateInterface.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ValidateCodeProcessorHolder {

   // @Autowired
   // private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
    //    ValidateCodeProcessor processor = validateCodeProcessors.get(name);
       // if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
       // }
      //  return processor;
    }
}
