package com.haoshuang.sso.demosso.common.validate;

import lombok.Data;

@Data
public class ImageCodeProperties {

    private int width = 60;
    private int height = 32;
    private int length = 4;
    // 有效期
    private int expireIn = 60;
    private String url;


}
