package com.haoshuang.sso.demosso.config.ValidateCode;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="core.security")
public class SecurityProperties {

    private ValidateCodeProperties code = new ValidateCodeProperties();


}
