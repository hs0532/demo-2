package com.haoshuang.sso.demosso.common.validate.validateInterface;

import com.haoshuang.sso.demosso.common.validate.ImageCode;
import com.haoshuang.sso.demosso.common.validate.ValidateCode;
import com.haoshuang.sso.demosso.common.validate.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图片验证码
 */
@Component("imageValidateCodeGenerator")
public class ImageValidateCodeGenerator implements ValidateCodeGenerator {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    //操作Session的类
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SecurityProperties securityProperties;



    @Override
    public ValidateCode generate(ServletWebRequest servletWebRequest) {
        return createImageCode(servletWebRequest);
    }

    //生成图片
    private ImageCode createImageCode(ServletWebRequest request) {
        //宽和高需要从request来取，如果没有传递，再从配置的值来取
        //验证码宽和高
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        Random random = new Random();

        graphics.setColor(getRandColor(200 ,250));
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font("Times New Roman", Font.ITALIC, 24));
        graphics.setColor(getRandColor(170,200));
        for(int i=0;i<80;i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x+xl, y+yl);
        }
        String sRand = "";
        //验证码长度
        for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand +=rand;
            graphics.setColor(new Color(14+random.nextInt(110), 14+random.nextInt(110),14+random.nextInt(110)));
            graphics.drawString(rand, 13*i+6, 16);
        }
        graphics.dispose();
        //过期时间
        return new ImageCode(image, sRand, securityProperties.getCode().getImage().getExpireIn());
    }

    //随机生成背景条纹
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc>255) {
            fc = 255;
        }
        if (bc>255) {
            bc = 255;
        }
        int r =  random.nextInt(bc - fc+1)+fc;
        int g =  random.nextInt(bc - fc+1)+fc;
        int b = random.nextInt(bc - fc+1)+fc;
        return new Color(r, g, b);
    }
}
