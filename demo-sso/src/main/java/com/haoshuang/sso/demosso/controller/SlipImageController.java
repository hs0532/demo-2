package com.haoshuang.sso.demosso.controller;

import com.haoshuang.sso.demosso.common.validate.ValidateCodeBean.PageData;
import com.haoshuang.sso.demosso.common.validate.ValidateCodeBean.VerifyImageCode;
import com.haoshuang.sso.demosso.common.validate.validateInterface.SlipImageValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping(value = "/check")
public class SlipImageController extends BaseController{

    @Autowired
    SlipImageValidateCodeGenerator verifyImageUtil;

    //获取滑动验证码的图片
    @RequestMapping(value = "/getImgSwipe", method = RequestMethod.GET)
    @ResponseBody
    public PageData getImgSwipe() {
        PageData pd = new PageData();
        String path = "";
        int index = 0;
        Random ran = new Random();
        HttpSession session = this.getCurrentSession();
        try {
            session.setAttribute("imgSwipeSuccess", "false");
            //随机获取verifyImages文件夹下的某一张图片
            String url = this.getClass().getResource("/").toString().substring(5);
            String resources = "/static/static/img/verifyImages";
            path = url + resources;
            File file = new File(path);
            File[] files = file.listFiles();

            index = ran.nextInt(files.length - 1);    //随机下标
            System.out.println(files[index].getPath());

            VerifyImageCode img = verifyImageUtil.getVerifyImage(files[index].getPath());
            pd.put("SrcImage", img.getSrcImage());
            pd.put("CutImage", img.getCutImage());
//			pd.put("XPosition",img.getXPosition());
            pd.put("YPosition", img.getYPosition());
            pd.put("SrcImageWidth", img.getSrcImageWidth());
            pd.put("SrcImageHeight", img.getSrcImageHeight());
            session.setAttribute("XPosition", img.getXPosition());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pd;
    }

    //返回前端滑动验证码参数
    @RequestMapping(value = "/rstImgSwipe", method = RequestMethod.POST)
    @ResponseBody
    public PageData rstImgSwipe() {
        PageData pd = this.getPageData();
        Float moveEnd_X = Float.valueOf(pd.getString("moveEnd_X"));
        Float wbili = Float.valueOf(pd.getString("wbili"));
        HttpSession session = this.getCurrentSession();
        Integer XPosition = Integer.parseInt(session.getAttribute("XPosition").toString());

        Float xResult = XPosition * wbili;
        System.out.println("mx:" + moveEnd_X + "\nmp:" + XPosition + "\nwbili:" + wbili + "\nxRst:" + xResult);
        PageData pd1 = new PageData();
        if (Math.abs(xResult - moveEnd_X) < 10) {    //设置误差像素

            pd1.put("success", true);
            session.setAttribute("imgSwipeSuccess", "ture");
            return pd1;
        } else {
            pd1.put("success", false);
            session.setAttribute("imgSwipeSuccess", "false");
            return pd1;
        }
    }

}
