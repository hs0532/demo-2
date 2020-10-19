package com.haoshuang.sso.demosso.controller;

import com.haoshuang.sso.demosso.config.ValidateCode.ImageCode;
import com.haoshuang.sso.demosso.config.ValidateCode.SecurityProperties;
import com.haoshuang.sso.demosso.config.ValidateCode.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

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


