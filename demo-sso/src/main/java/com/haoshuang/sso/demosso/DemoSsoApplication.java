package com.haoshuang.sso.demosso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan({"com.haoshuang.sso.demosso.Mapper"})
//@ComponentScan( {"com.haoshuang.sso.demosso.bean"})
public class DemoSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSsoApplication.class, args);
    }

}
