package com.haoshuang.sso.demosso.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haoshuang.sso.demosso.common.ResultBean;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
@Slf4j
public class MyFailAuthenticationFailHandler implements AuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(MyFailAuthenticationFailHandler.class);

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("登陆失败log4j");
        logger.info("登录失败");
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(200);
        String reson = null;
        if (e instanceof BadCredentialsException ||
                e instanceof UsernameNotFoundException) {
            reson="账户名或者密码输入错误!";
        } else if (e instanceof LockedException) {
            reson="账户被锁定，请联系管理员!";
        } else if (e instanceof CredentialsExpiredException) {
            reson="密码过期，请联系管理员!";
        } else if (e instanceof AccountExpiredException) {
            reson="账户过期，请联系管理员!";
        } else if (e instanceof DisabledException) {
            reson="账户被禁用，请联系管理员!";
        } else {
            reson="其他未知原因原因!";
            log.info("用户登录失败，失败原因{}",e.toString());
        }

        httpServletResponse.setStatus(200);
        ResultBean resultBean = new ResultBean();
        resultBean.setCade(1);
        resultBean.setMsg(reson);
        PrintWriter out =  httpServletResponse.getWriter();
        //.write(objectMapper.writeValueAsString(e));
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(resultBean));
        out.flush();
        out.close();

    }
}
