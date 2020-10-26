package com.haoshuang.sso.demosso.common.validate.ValidateCodeBean;

import com.haoshuang.sso.demosso.common.validate.ValidateCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VerifyImageCode extends ValidateCode {

    private String srcImage;
    private String cutImage;
    private int xPosition;
    private int yPosition;
    private int srcImageWidth;
    private int srcImageHeight;

    public VerifyImageCode(String code, int expireIn, String srcImage, String cutImage, int xPosition, int yPosition, int srcImageWidth, int srcImageHeight) {
        super(code, expireIn);
        this.srcImage = srcImage;
        this.cutImage = cutImage;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.srcImageWidth = srcImageWidth;
        this.srcImageHeight = srcImageHeight;
    }
}
