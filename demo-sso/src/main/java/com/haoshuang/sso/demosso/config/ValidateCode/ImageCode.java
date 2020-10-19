package com.haoshuang.sso.demosso.config.ValidateCode;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Data
public class ImageCode {

    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime; // 过期时间

    /**
     * @param image
     * @param code
     * @param expireIn 过期时间，单位秒
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    // 是否过期
    public boolean isExpried() {
        return this.expireTime.isBefore(LocalDateTime.now());
    }
}