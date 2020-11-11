package com.haoshuang.sso.demosso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("demo")
public class DemoController {
    @RequestMapping("test")
    public String test(){
        return "test";
    }

    @RequestMapping("slip")
    public String slipvalidate(){
        return "slipvalidate";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
