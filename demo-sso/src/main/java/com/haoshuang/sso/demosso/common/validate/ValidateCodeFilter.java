package com.haoshuang.sso.demosso.common.validate;


import com.haoshuang.sso.demosso.common.validate.ValidateCodeException.ValidateCodeException;
import com.haoshuang.sso.demosso.config.MyFailAuthenticationFailHandler;
import com.haoshuang.sso.demosso.common.validate.validateInterface.ValidateCodeProcessor;
import com.haoshuang.sso.demosso.common.validate.validateInterface.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 图片验证码验证过滤器
 * OncePerRequestFilter spring提供的，保证在一个请求中只会被调用一次
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {
    // 在初始化本类的地方进行注入
    // 一般在配置security http的地方进行添加过滤器
    @Autowired
    private CodeFailException failureHandler;

    @Autowired
    ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();
    private Map<String,ValidateCodeType> urlMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        /**
         * 此处地址可又配置文件读取
         */
        urlMap.put("/authentication/form",ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(),ValidateCodeType.IMAGE);
    }

    protected  void addUrlToMap(String urlString,ValidateCodeType type){
        if(StringUtils.isNotBlank(urlString)){
            String[] strings = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString,",");
            for (String url: strings) {
                urlMap.put(url,type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType type =getValidateCodeType(request);
        // 为登录请求，并且为post请求【即登录请求时，先验证图片验证码】
        if (null != type){
            try {
                ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorHolder.findValidateCodeProcessor(type);
                validateCodeProcessor.validate(new ServletWebRequest(request,response));//验证
            } catch (ValidateCodeException e) {
                failureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 获取当前请求的验证码类型
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request){
        ValidateCodeType type = null;
        if(StringUtils.endsWithIgnoreCase(request.getMethod(),"post")){
            Set<String> urls = urlMap.keySet();
            for (String url:urls){
                if(pathMatcher.match(url,request.getRequestURI())){
                    type = urlMap.get(url);
                }
            }
        }
        return type;
    }


}
