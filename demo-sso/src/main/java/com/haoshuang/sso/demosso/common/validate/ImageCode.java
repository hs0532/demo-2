package com.haoshuang.sso.demosso.common.validate;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ImageCode extends ValidateCode{

    private BufferedImage image;

    /**
     * @param image
     * @param code
     * @param expireIn 过期时间，单位秒
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image,String code,LocalDateTime exprieTime){
        super(code,exprieTime);
        this.image = image;
    }

}