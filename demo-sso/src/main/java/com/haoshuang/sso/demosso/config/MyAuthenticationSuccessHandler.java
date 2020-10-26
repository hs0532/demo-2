package com.haoshuang.sso.demosso.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haoshuang.sso.demosso.common.ResultBean;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Log4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        // 登录成功后把authentication返回给前台
        response.setContentType("application/json;charset=UTF-8");

        ResultBean resultBean = new ResultBean();
        resultBean.setCade(0);
        resultBean.setMsg("登陆成功");
        PrintWriter out =  response.getWriter();
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest!=null){
            String url = savedRequest.getRedirectUrl();
            response.sendRedirect(url);
            resultBean.setData(url);
        }

        //.write(objectMapper.writeValueAsString(e));
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(resultBean));
        out.flush();
        out.close();
        //response.getWriter().write(objectMapper.writeValueAsString(authentication));

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

    }
}
