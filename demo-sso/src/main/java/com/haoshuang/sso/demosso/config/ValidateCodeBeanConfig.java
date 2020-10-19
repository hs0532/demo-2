package com.haoshuang.sso.demosso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {
//    @Autowired
//    private SecurityProperties securityProperties;
//
//    @Bean
//    @ConditionalOnMissingBean(name = "imageCodeGenerator")
//    public ValidateCodeGenerator imageCodeGenerator() {
//        System.out.println("init imageCodeGenerator");
//        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
//        codeGenerator.setSecurityProperties(securityProperties);
//        return codeGenerator;
//    }
}
