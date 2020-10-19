package com.haoshuang.sso.demosso.config.ValidateCode.validateInterface;

import com.alibaba.druid.util.Base64;
import com.haoshuang.sso.demosso.config.ValidateCode.ImageCode;
import com.haoshuang.sso.demosso.config.ValidateCode.validateInterface.AbstractValidateCodeProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;


@Component("imageValidateCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {


    //
    @Override
    public String sending(ServletWebRequest servletWebRequest, ImageCode validateCode) throws Exception {
        ByteArrayOutputStream bso = new ByteArrayOutputStream();
        ImageIO.write(validateCode.getImage(),"JPEG",servletWebRequest.getResponse().getOutputStream());
        String mageStr = Base64.byteArrayToBase64(bso.toByteArray());
        return "data:image/jpeg;Base64,"+mageStr;
    }

    @Override
    public void send(ServletWebRequest servletWebRequest, ImageCode validateCode) throws Exception {
        ImageIO.write(validateCode.getImage(),"JPEG",servletWebRequest.getResponse().getOutputStream());
    }
}
