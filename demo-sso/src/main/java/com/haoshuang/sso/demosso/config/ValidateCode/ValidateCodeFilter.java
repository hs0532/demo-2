package com.haoshuang.sso.demosso.config.ValidateCode;


import com.haoshuang.sso.demosso.config.MyFailAuthenticationFailHandler;
import com.haoshuang.sso.demosso.config.ValidateCode.ValidateCodeException.ValidateCodeException;
import com.haoshuang.sso.demosso.controller.ValidateCodeController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片验证码验证过滤器
 * OncePerRequestFilter spring提供的，保证在一个请求中只会被调用一次
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {
    // 在初始化本类的地方进行注入
    // 一般在配置security http的地方进行添加过滤器
    @Autowired
    private MyFailAuthenticationFailHandler failureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 为登录请求，并且为post请求【即登录请求时，先验证图片验证码】
        if (StringUtils.equals("/authentication/form", request.getRequestURI())
                && StringUtils.equalsAnyIgnoreCase(request.getMethod(), "post")) {
            try {
                validate(request);//验证
            } catch (ValidateCodeException e) {
                failureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        // 拿到之前存储的imageCode信息
        ServletWebRequest swr = new ServletWebRequest(request);
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(swr, ValidateCodeController.SESSION_KEY);
        // 又是一个spring中的工具类，
        // 试问一下，如果不看源码怎么可能知道有这些工具类可用？
        String codeInRequest = ServletRequestUtils.getStringParameter(request, "imageCode");
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(swr, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        //移除验证码
        sessionStrategy.removeAttribute(swr, ValidateCodeController.SESSION_KEY);
    }

}
