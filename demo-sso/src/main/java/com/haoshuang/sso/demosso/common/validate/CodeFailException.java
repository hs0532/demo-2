package com.haoshuang.sso.demosso.common.validate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haoshuang.sso.demosso.common.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CodeFailException implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("验证码错误");
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(200);
        ObjectMapper objectMapper = new ObjectMapper();
       httpServletResponse.getWriter().write(objectMapper.writeValueAsString( ResultBean.createByError(e.getMessage())));
    }
}
