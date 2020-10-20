package com.haoshuang.sso.demosso.controller;

import com.haoshuang.sso.demosso.common.validate.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ValidateCodeController {

    @Autowired
    ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.根据随机数生成数字
        validateCodeProcessorHolder.findValidateCodeProcessor("image").create(new ServletWebRequest(request,response));
    }


}


